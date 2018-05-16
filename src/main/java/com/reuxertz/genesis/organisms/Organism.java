package com.reuxertz.genesis.organisms;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.registry.GenesisRegistry;
import com.reuxertz.genesis.registry.RegistryObject;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class Organism {

    public String name;
    public Genome genome;
    public Metabolism metabolism;
    public RegistryObject registryObject;

    protected double energy;

    public Organism(String name, Genome genome, Metabolism metabolism)
    {
        this.genome = genome;
        this.metabolism = metabolism;
        this.name = name;

        this.registryObject = Genesis.registry.getRegistryObject(name);

        return;
    }

    public void tick(World world)
    {

    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        genome.writeToNBT(nbt);
        metabolism.writeToNBT(nbt);

        nbt.setString("name", name);
        nbt.setDouble("energy", energy);
    }

    public static Organism readFromNBT(NBTTagCompound nbt)
    {
        Genome genome = Genome.readFromNBT(nbt);
        Metabolism metabolism = Metabolism.readFromNBT(nbt);
        String organismName = nbt.getString("name");

        Organism organism = new Organism(organismName, genome, metabolism);
        organism.energy = nbt.getDouble("energy");

        return organism;
    }
}
