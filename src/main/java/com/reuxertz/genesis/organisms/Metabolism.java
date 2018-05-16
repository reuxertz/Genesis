package com.reuxertz.genesis.organisms;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class Metabolism {

    protected double energy;

    //public static final EnergyLostPerTick =

    public Metabolism()
    {

    }

    public void tick(World world)
    {

    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        nbt.setDouble("energy", energy);
    }

    public static Metabolism readFromNBT(NBTTagCompound nbt)
    {
        Metabolism metabolism = new Metabolism();
        metabolism.energy = nbt.getDouble("energy");

        return metabolism;
    }
}
