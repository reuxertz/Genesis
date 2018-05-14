package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.api.genes.GeneData;
import com.reuxertz.genesis.organisms.Genome;

import java.util.HashMap;
import java.util.List;

public class SpeciesRegistry {

    public static HashMap<String, List<GeneData>> speciesRegistry = new HashMap<>();

    public static void registerSpecies(String speciesName, List<GeneData> geneData)
    {
        speciesRegistry.put(speciesName, geneData);
    }
    public static Genome getSpeciesGenome(String speciesName)
    {
        return new Genome(speciesRegistry.get(speciesName));
    }
}
