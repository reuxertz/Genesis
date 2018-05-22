package com.reuxertz.genesis.organisms;

import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.util.EnergyHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import static com.reuxertz.genesis.util.EnergyHelper.convertDayToTick;
import static com.reuxertz.genesis.util.EnergyHelper.EnergyCostToGramExponent;

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
    public void handleGrowth(World world)
    {
        double mass = organism.mass;

        organism.organismContainer.handleGrowth();

        double growthFactorGene = GenomeHelper.expressValue(organism.genome, GeneData.GeneType.GrowthFactor, GenomeHelper.ExpressionType.Sigmoid);

        organism.mass += 1;



        return;
    }
}
