package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.OrganismData;
import com.reuxertz.genesis.organisms.Genome;

import java.util.HashMap;
import java.util.List;

public class SpeciesRegistry {

    private static class SpeciesRegistryObject
    {
        public OrganismData organismData;
        public List<GeneData> geneData;

        public SpeciesRegistryObject(OrganismData organismData, List<GeneData> geneData)
        {
            this.organismData = organismData;
            this.geneData = geneData;
        }
    }

    public static HashMap<String, SpeciesRegistryObject> speciesRegistry = new HashMap<>();

    public static void registerSpecies(String speciesName, OrganismData organismData, List<GeneData> geneData)
    {
        speciesRegistry.put(speciesName, new SpeciesRegistryObject(organismData, geneData));
    }
    public static Genome getSpeciesGenome(String speciesName)
    {
        return new Genome(speciesRegistry.get(speciesName).geneData);
    }
    public static OrganismData getSpeciesOrganismData(String speciesName)
    {
        return speciesRegistry.get(speciesName).organismData;
    }
}
