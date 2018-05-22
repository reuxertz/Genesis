package com.reuxertz.genesis.organisms;

import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
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
    public double getMass() { return mass; }
    public boolean isDead() { return metabolism.energy <= 0 || mass <= 0; }

    public double getGrowthStateByMass()
    {
//        SpeciesFeature speciesAdultMass = SpeciesRegistry.getSpeciesFeature(name, SpeciesFeature.FeatureTypes.AdultMass);
//        GeneData speciesAdultMassGene = genome.getGene(GeneData.GeneType.MassFactor);

        double adultMassFactor = GenomeHelper.expressValue(name, SpeciesFeature.FeatureTypes.AdultMass, genome, GeneData.GeneType.MassFactor, GenomeHelper.ExpressionType.PseudoLinear);
        double growthStage = mass / adultMassFactor;

        return growthStage;
    }

    public Organism(String name, Genome genome, Metabolism metabolism)
    {
        this.genome = genome;
        this.metabolism = metabolism;
        this.name = name;

        this.tickCounter = new TickCounter(random, true);

        metabolism.organism = this;

        return;
    }
    public Organism(String name, Genome genome, Metabolism metabolism, double mass) {
        this(name, genome, metabolism);
        this.mass = mass;
    }

    public void tick(World world)
    {
        if (world.isRemote)
            return;

        int tickCount = tickCounter.getTicks(world.getTotalWorldTime());

        if (tickCount > 0) {
            metabolism.handleGrowth(world);
            for (int i = 0; i < tickCount; i++)
            {

            }


            organismContainer.refreshState();
        }

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
