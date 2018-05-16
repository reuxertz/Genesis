package com.reuxertz.genesis.tileentity;

import com.reuxertz.genesis.organisms.Organism;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TileEntityBaseCrop extends BaseTileEntity {

    public Organism organism;

    public TileEntityBaseCrop(Organism organism)
    {
        this.organism = organism;
    }

    public void tick(World world)
    {
        organism.tick(world);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        organism.writeToNBT(nbt);
        return nbt;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        organism = Organism.readFromNBT(nbt);
    }
}
