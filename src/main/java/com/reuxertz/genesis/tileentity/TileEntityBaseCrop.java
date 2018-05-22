package com.reuxertz.genesis.tileentity;

import com.reuxertz.genesis.api.blocks.BaseBlockCrop;
import com.reuxertz.genesis.organisms.IOrganismContainer;
import com.reuxertz.genesis.organisms.Organism;
import com.reuxertz.genesis.util.EnergyHelper;
import com.reuxertz.genesis.util.RandomHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

import static com.reuxertz.genesis.util.EnergyHelper.SunlightEnergyPerGramTick;

public class TileEntityBaseCrop extends BaseTileEntity implements
        //ITickable,
        IOrganismContainer  {

    protected Organism organism;

    public Organism getOrganism() { return organism; }

    public TileEntityBaseCrop(Organism organism)
    {
        this.organism = organism;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        organism.writeToNBT(nbt);
        return nbt;
    }

//    @Override
//    public void update() {
//        getOrganism().tick(world);
//    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        organism = Organism.readFromNBT(this, nbt);
    }

    public void handleGrowth()
    {
        double limit = 9;
        double light = world.getLightFromNeighbors(pos.up());
        double lightFactor = (light - limit) / (15.0 - limit);
        if (lightFactor > 0)
        {
            double lastTickDif = organism.getTickCounter().getLastTickDif();
            double energyInput = SunlightEnergyPerGramTick * lastTickDif * organism.getMass() * lightFactor;

            //double t = SunlightEnergyPerGramTick * (20 * 60 * 20) * 10;

            organism.getMetabolism().addEnergy(energyInput);

            double energyCostPerGramTick = EnergyHelper.getEnergyCostPerGramTick(organism.getMass());
            //double x = energyCostPerGramTick * 20 * 60 * 20;
            double energyCost = energyCostPerGramTick * lastTickDif * organism.getMass();

            double energyChange = energyInput - energyCost;

            organism.getMetabolism().addEnergy(energyChange);
            return;
        }

        return;
    }

    public void refreshState()
    {
        if (organism.isDead()) {
            world.destroyBlock(pos, false);
            return;
        }


        double growthStage = organism.getGrowthStateByMass();

        IBlockState state = world.getBlockState(pos);
        int i = ((BaseBlockCrop)state.getBlock()).getAge(state);
        if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(world, pos, state, true))
        {
            if (i < 7)
                world.setBlockState(pos, ((BlockCrops)state.getBlock()).withAge(i + 1), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(world, pos, state, world.getBlockState(pos));
        }
    }
}
