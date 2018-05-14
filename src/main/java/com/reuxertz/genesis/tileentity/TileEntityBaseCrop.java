package com.reuxertz.genesis.tileentity;

import com.reuxertz.genesis.organisms.Genome;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityBaseCrop extends BaseTileEntity {

    public Genome genome;

    public TileEntityBaseCrop(Genome genome)
    {
        this.genome = genome;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        genome.writeToNBT(nbt);
        return nbt;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        genome = Genome.readFromNBT(nbt);
    }
}
