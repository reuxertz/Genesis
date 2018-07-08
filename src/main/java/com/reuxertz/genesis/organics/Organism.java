package com.reuxertz.genesis.organics;

import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.util.EnergyHelper;
import com.reuxertz.genesis.util.MathHelper;
import com.reuxertz.genesis.util.TickCounter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.Random;
import java.util.UUID;

import static com.reuxertz.genesis.util.EnergyHelper.EnergyStorageDensityPerGram;

public class Organism {

    public static Random random = new Random();

    protected UUID uuid = UUID.randomUUID();
    protected String name;
    protected Genome genome;
    //protected Metabolism metabolism;
    protected IOrganismContainer organismContainer;
    protected TickCounter tickCounter;

    protected long ticksTimeAlive;

    protected double mass;
    protected double energy;
    protected double newbornCount;

    protected boolean maleFemaleSwitch;

    protected double growthRateValue;
    protected double adultMassValue;
    protected double adultAgeTicksValue;
    protected double newBornMassValue;
    protected double clutchSizeValue;

    public TickCounter getTickCounter() { return tickCounter; }
    public Genome getGenome() { return genome; }

    public void addEnergy(double energy)
    {
        this.energy += energy;
    }
    public void setOrganismContainer(IOrganismContainer organismContainer)
    {
        this.organismContainer = organismContainer;
    }

    public double getEnergy()
    {
        return energy;
    }
    public double getExcessEnergy()
    {
        double rawExcessEnergy = energy - EnergyHelper.getEnergyStorageCapacity(mass);
        if (rawExcessEnergy < 0)
            return 0;
        else
            return rawExcessEnergy;
    }
    public double getMass() { return mass; }
    public double getNewbornCount() { return newbornCount; }
    public boolean isDead() { return energy <= 0 || mass <= 0; }
    public double getAgeState()
    {
        return (ticksTimeAlive - adultAgeTicksValue) / adultAgeTicksValue;
    }
    public double getGrowthState()
    {
        if (adultAgeTicksValue == 0) {
            double growthStage = (mass + newbornCount * newBornMassValue) / adultMassValue;
            return growthStage;
        }

        double ageState = getAgeState();
        return ageState;

    }
    public double getAgeMaxMass()
    {
        if (adultAgeTicksValue == 0) {
            return adultMassValue;
        }

        double adultMassFactor = getGrowthState();
        if (adultMassFactor > 1)
            adultMassFactor = 1;

        return adultMassFactor * adultMassValue;
    }

    public Organism(String name, Genome genome)
    {
        this.genome = genome;
        this.name = name;
        this.tickCounter = new TickCounter(random, true);

        initializeGenome();

        return;
    }
    public Organism(String name, Genome genome, double age, double mass) {
        this(name, genome);
        this.mass = mass;
        this.energy = EnergyHelper.getEnergyStorageCapacity(mass);
    }

    public void initializeGenome()
    {
        //GenomeHelper.validateGenome(name, genome);

        growthRateValue = GenomeHelper.expressValue(genome, GeneData.GeneType.GrowthFactor, GenomeHelper.ExpressionType.Sigmoid);
        adultMassValue = GenomeHelper.expressValue(name, SpeciesFeature.FeatureTypes.AdultMass, genome, GeneData.GeneType.AdultMassFactor, GenomeHelper.ExpressionType.PseudoLinear);
        adultAgeTicksValue = GenomeHelper.expressValue(name, SpeciesFeature.FeatureTypes.AdultAgeTicks, genome, GeneData.GeneType.AdultAgeTicksFactor, GenomeHelper.ExpressionType.PseudoLinear);
        newBornMassValue = GenomeHelper.expressValue(name, SpeciesFeature.FeatureTypes.NewbornMass, genome, GeneData.GeneType.NewBornMassFactor, GenomeHelper.ExpressionType.PseudoLinear);
        clutchSizeValue = GenomeHelper.expressValue(name, SpeciesFeature.FeatureTypes.ClutchSize, genome, GeneData.GeneType.ClutchSizeFactor, GenomeHelper.ExpressionType.PseudoLinear);
    }

    public void tick(World world)
    {
        if (world.isRemote)
            return;

        int tickCount = tickCounter.getTicks(world.getTotalWorldTime());
        ticksTimeAlive += tickCount * tickCounter.getWaitTicks();

        if (tickCount > 0) {
            handleMetabolism(world);
            handleReproduction();
            organismContainer.refreshState();
        }

    }

    public int removeNewborn()
    {
        if (newbornCount >= 1) {
            newbornCount--;
            return 1;
        }
        return 0;
    }

    public boolean handleReproduction()
    {
        double newBornPotential = (getExcessEnergy() / newBornMassValue);
        if (newBornPotential < 1)
            return false;

        boolean loseEnergy = true;
        if (newbornCount < clutchSizeValue)
            newbornCount++;
        else
            loseEnergy = organismContainer.handleReproduction();

        if (loseEnergy) {

            double nbmv = EnergyHelper.getEnergy(newBornMassValue);
            double nbmvsc = EnergyHelper.getEnergyStorageCapacity(newBornMassValue);

            double parentEnergyLoss = -1.0 * (nbmv + nbmvsc);
            addEnergy(parentEnergyLoss);
        }

        return true;
    }

    public void handleMetabolism(World world)
    {
        boolean side = world.isRemote;

        //Handle Container Growth
        organismContainer.handleGrowth();

        //Handle Decay
        double mss = mass;
        if (mass <= 0)
            mss = 1;

        double energyCostPerGramTick = EnergyHelper.getEnergyLossPerGramTick(mss);
        //double x = energyCostPerGramTick * 20 * 60 * 20;
        double energyCost = energyCostPerGramTick * tickCounter.getLastTickDif() * mss;
        energy -= energyCost;

        //Handle Mass Growth
        double growthEnergy = growthRateValue * energy;
        energy -= growthEnergy;

        double ageMaxMass = getAgeMaxMass();
        double maxMassGrowth = ageMaxMass - getMass();
        double maxEnergyIntake = maxMassGrowth * EnergyStorageDensityPerGram;

        if (maxEnergyIntake < growthEnergy)
            growthEnergy = maxEnergyIntake;

        mass += growthEnergy * EnergyStorageDensityPerGram;

        //Handle Excess Energy
        double excessEnergy = getExcessEnergy();
        if (excessEnergy > 0)
        {
            double excessLossFactor = MathHelper.Sigmoid(excessEnergy / adultMassValue, 1.0);
            double excessLoss = excessEnergy * excessLossFactor;

            energy -= excessLoss;

            return;
        }

        return;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        genome.writeToNBT(nbt);

        nbt.setString("name", name);
        nbt.setDouble("mass", mass);
        nbt.setLong("ticksTimeAlive", ticksTimeAlive);
        nbt.setDouble("getEnergy", energy);
        nbt.setDouble("newbornCount", newbornCount);

        return nbt;
    }

    public static Organism readFromNBT(IOrganismContainer organismContainer, NBTTagCompound nbt)
    {
        Genome genome = Genome.readFromNBT(nbt);
        String organismName = nbt.getString("name");
        if (organismName == null || organismName == "")
            organismName = organismContainer.getRegistryObject().name;

        Organism organism;
        if (GenomeHelper.validateGenome(organismName, "", genome))
        {
            organism = new Organism(organismName, genome);
            organism.setOrganismContainer(organismContainer);



            organism.mass = nbt.getDouble("mass");
            organism.energy = nbt.getDouble("getEnergy");
            organism.name = nbt.getString("name");
            organism.ticksTimeAlive = nbt.getLong("ticksTimeAlive");
            organism.newbornCount = nbt.getDouble("newbornCount");
        }
        else
        {
            organism = new Organism(organismName, genome);
            organism.setOrganismContainer(organismContainer);
        }

        return organism;
    }
}
