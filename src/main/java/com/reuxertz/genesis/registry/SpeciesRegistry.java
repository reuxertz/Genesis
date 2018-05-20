package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesData;
import com.reuxertz.genesis.organisms.Genome;

import java.util.HashMap;
import java.util.List;

public class SpeciesRegistry {

    private static class SpeciesRegistryObject
    {
        public List<SpeciesData> speciesData;
        public List<GeneData> geneData;
        public String speciesName;

        public SpeciesRegistryObject(String speciesName, List<SpeciesData> speciesData, List<GeneData> geneData)
        {
            this.speciesData = speciesData;
            this.geneData = geneData;
            this.speciesName = speciesName;
        }
    }

    public static HashMap<String, SpeciesRegistryObject> speciesRegistry = new HashMap<>();

    public static void registerSpecies(String speciesName, List<SpeciesData> speciesData, List<GeneData> geneData)
    {
        speciesRegistry.put(speciesName, new SpeciesRegistryObject(speciesName, speciesData, geneData));
    }
    public static Genome getSpeciesGenome(String speciesName)
    {
        return new Genome(speciesRegistry.get(speciesName).geneData);
    }
    public static List<SpeciesData> getSpeciesData(String speciesName)
    {
        return speciesRegistry.get(speciesName).speciesData;
    }
}
