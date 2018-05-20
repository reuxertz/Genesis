package com.reuxertz.genesis.tileentity;

import com.reuxertz.genesis.organisms.IOrganismContainer;
import com.reuxertz.genesis.organisms.Organism;
import net.minecraft.nbt.NBTTagCompound;

import static com.reuxertz.genesis.util.EnergyHelper.SunlightEnergyPerBlockTick;

public class TileEntityBaseCrop extends BaseTileEntity implements IOrganismContainer  {

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

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        organism = Organism.readFromNBT(this, nbt);
    }

    public void handleGrowth()
    {
        int limit = 9;
        int light = world.getLightFromNeighbors(pos.up());
        double lightFactor = (light - limit) / (15 - limit);
        if (lightFactor > 0)
        {
            double lastTickDif = organism.getTickCounter().getLastTickDif();
            double energyInput = SunlightEnergyPerBlockTick * lastTickDif;

            organism.getMetabolism().addEnergy(energyInput);
            return;
        }

        return;
    }

    public void handleDeath()
    {
        world.destroyBlock(pos, false);
    }
}
