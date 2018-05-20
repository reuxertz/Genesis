package com.reuxertz.genesis.organisms;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class Metabolism {

    protected Organism organism;

    protected double energy;

    //public static final EnergyLostPerTick =

    public void addEnergy(double energy)
    {
        this.energy += energy;
    }

    public Metabolism()
    {

    }

    public void tick(World world, Random rand)
    {
        HandleGrowth(world);
        HandleDeath(world);
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

    //This is obviously bad and can definitely be refactored
    public void HandleGrowth(World world)
    {
        organism.organismContainer.handleGrowth();

        return;
    }

    //This is obviously bad and can definitely be refactored
    public void HandleDeath(World world)
    {
        if (energy <= 0)
        {
            organism.organismContainer.handleDeath();
        }
    }
}
