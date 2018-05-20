package com.reuxertz.genesis.organisms;

import com.reuxertz.genesis.util.TickCounter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.Random;

public class Organism {

    public static Random random = new Random();

    protected String name;
    protected Genome genome;
    protected Metabolism metabolism;
    protected IOrganismContainer organismContainer;

    protected TickCounter tickCounter;

    protected double mass;

    public void setOrganismContainer(IOrganismContainer organismContainer)
    {
        this.organismContainer = organismContainer;
    }

    public Metabolism getMetabolism() { return metabolism; }
    public TickCounter getTickCounter() { return tickCounter; }

    public Organism(String name, Genome genome, Metabolism metabolism)
    {
        this.genome = genome;
        this.metabolism = metabolism;
        this.name = name;

        this.tickCounter = new TickCounter(random, true);

        metabolism.organism = this;

        return;
    }

    public void tick(World world, Random rand)
    {
        if (tickCounter.isTicked(world.getTotalWorldTime()))
            metabolism.tick(world, rand);


    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        genome.writeToNBT(nbt);
        metabolism.writeToNBT(nbt);

        nbt.setString("name", name);
        nbt.setDouble("mass", mass);
    }

    public static Organism readFromNBT(IOrganismContainer organismContainer, NBTTagCompound nbt)
    {
        Genome genome = Genome.readFromNBT(nbt);
        Metabolism metabolism = Metabolism.readFromNBT(nbt);
        String organismName = nbt.getString("name");

        Organism organism = new Organism(organismName, genome, metabolism);
        organism.setOrganismContainer(organismContainer);

        organism.mass = nbt.getDouble("mass");

        return organism;
    }
}
