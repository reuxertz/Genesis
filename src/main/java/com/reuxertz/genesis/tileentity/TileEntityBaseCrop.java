package com.reuxertz.genesis.tileentity;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.api.blocks.BaseBlockGrowable;
import com.reuxertz.genesis.organics.IOrganismContainer;
import com.reuxertz.genesis.organics.Organism;
import com.reuxertz.genesis.registry.RegistryObject;
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

    protected RegistryObject registryObject;
    protected Organism organism;
    protected String name;

    public World getWorld() { return world; }
    public RegistryObject getRegistryObject() { return registryObject; }
    public Organism getOrganism() { return organism; }

    public TileEntityBaseCrop()
    {
    }
    public TileEntityBaseCrop(Organism organism, String name)
    {
        this.organism = organism;
        this.name = name;

        registryObject = Genesis.registry.getRegistryObject(name);
    }

    @Override
    public void update() {
        //getOrganism().tick(world);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        NBTTagCompound nbt = super.writeToNBT(compound);
        organism.writeToNBT(nbt);

        nbt.setString("name", name);

        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        organism = Organism.readFromNBT(this, nbt);

        name = nbt.getString("name");

        registryObject = Genesis.registry.getRegistryObject(name);
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

        double growthStage = organism.getGrowthStateByTotalMass();
        growthStage = growthStage * 6.99;


        if (growthStage > 7)
            growthStage = 7;
        int growthStageInt = (int)growthStage;

        IBlockState state = world.getBlockState(pos);
        int actualGrowthStage = ((BaseBlockGrowable)state.getBlock()).getAge(state);
        if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(world, pos, state, true))
        {
            if (actualGrowthStage != growthStageInt && actualGrowthStage < 8)
                world.setBlockState(pos, ((BlockCrops)state.getBlock()).withAge(growthStageInt));

            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(world, pos, state, world.getBlockState(pos));
        }
    }
}
