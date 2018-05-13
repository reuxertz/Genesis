package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.genetics.Genome;

import java.util.HashMap;

public class SpeciesRegistry {

    public static HashMap<String, Genome> speciesRegistry = new HashMap<>();

    public static void registerSpecies(String speciesName, Genome genome)
    {
        speciesRegistry.put(speciesName, genome);
    }
    public static Genome getSpeciesGenome(String speciesName)
    {
        return speciesRegistry.get(speciesName);
    }
}
