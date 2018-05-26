package com.reuxertz.genesis.tileentity;

import com.reuxertz.genesis.api.blocks.BaseBlockGrowable;
import com.reuxertz.genesis.organics.IOrganismContainer;
import com.reuxertz.genesis.organics.Organism;
import com.reuxertz.genesis.util.BlockHelper;
import com.reuxertz.genesis.util.EnergyHelper;
import com.reuxertz.genesis.util.RandomHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.reuxertz.genesis.util.EnergyHelper.SunlightEnergyPerGramTick;

public class TileEntityBaseCrop extends BaseTileEntity implements
        ITickable,
        IOrganismContainer  {

    protected Organism organism;

    public World getWorld() { return world; }

    public Organism getOrganism() { return organism; }

    public TileEntityBaseCrop()
    {
    }
    public TileEntityBaseCrop(Organism organism)
    {
        this.organism = organism;
    }

    @Override
    public void update() {
        getOrganism().tick(world);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        boolean b = world.isRemote;
        NBTTagCompound nbt = super.writeToNBT(compound);
        organism.writeToNBT(nbt);

        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        organism = Organism.readFromNBT(this, nbt);
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return false;
        //return oldState.getBlock() != newState.getBlock();
    }


    public void handleGrowth()
    {
        double limit = 9;
        double light = world.getLightFromNeighbors(pos.up());
        double lightFactor = (light - limit) / (15.0 - limit);
        if (lightFactor > 0)
        {
            double lastTickDif = organism.getTickCounter().getLastTickDif();

            Block cropBlock = world.getBlockState(pos).getBlock();
            IBlockState soilBlock = world.getBlockState(pos.down());

            double soilFactor = 1.0;
            if (soilBlock.getBlock() != Blocks.FARMLAND)
                soilFactor *= .5;

            //double t = SunlightEnergyPerGramTick * (20 * 60 * 20) * 10;

            double energyInput = SunlightEnergyPerGramTick * lastTickDif * organism.getMass() * lightFactor * soilFactor;
            organism.addEnergy(energyInput);
            return;
        }

        return;
    }

    public boolean handleReproduction()
    {
        BlockPos randBlockPos = BlockHelper.getRandomBlock(pos.down(), 4, 2, RandomHelper.random);

        BaseBlockGrowable parentBlock = (BaseBlockGrowable)world.getBlockState(pos).getBlock();
        IBlockState block = world.getBlockState(randBlockPos);
        IBlockState topBlock = world.getBlockState(randBlockPos.up());

        if (parentBlock.canBlockSustainGenesisPlant(block) && topBlock.getBlock() == Blocks.AIR) {
            world.setBlockState(randBlockPos.up(), parentBlock.getDefaultState());

            TileEntityBaseCrop tileEntityBaseCrop = (TileEntityBaseCrop)world.getTileEntity(randBlockPos.up());

            double parentEnergyLoss = -1.0 * (EnergyHelper.getEnergyContent(tileEntityBaseCrop.organism.getMass()) + tileEntityBaseCrop.organism.energy());
            organism.addEnergy(parentEnergyLoss);

            return true;
        }


        return false;
    }

    public void refreshState()
    {
        if (organism.isDead()) {
            world.destroyBlock(pos, false);
            return;
        }

        double growthStage = organism.getGrowthStateByMass();
        int growthStageInt = (int)(growthStage * 7.0);

        IBlockState state = world.getBlockState(pos);
        int actualGrowthStage = ((BaseBlockGrowable)state.getBlock()).getAge(state);
        if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(world, pos, state, true))
        {
            if (actualGrowthStage != growthStageInt && actualGrowthStage < 8)
                world.setBlockState(pos, ((BlockCrops)state.getBlock()).withAge(growthStageInt));
            else
                growthStage = growthStage;

            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(world, pos, state, world.getBlockState(pos));
        }
    }
}
