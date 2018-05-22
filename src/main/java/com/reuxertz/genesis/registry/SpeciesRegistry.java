package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.organisms.Genome;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpeciesRegistry {

    private static class SpeciesRegistryObject
    {
        public List<SpeciesFeature> speciesData;
        public Map<SpeciesFeature.FeatureTypes, SpeciesFeature> speciesDataMap = new HashMap<>();
        public List<GeneData> geneData;
        public String speciesName;

        public SpeciesRegistryObject(String speciesName, List<SpeciesFeature> speciesData, List<GeneData> geneData)
        {
            this.speciesData = speciesData;
            this.geneData = geneData;
            this.speciesName = speciesName;

            for (int i = 0; i < speciesData.size(); i++)
                speciesDataMap.put(speciesData.get(i).featureType, speciesData.get(i));
        }
    }

    public static HashMap<String, SpeciesRegistryObject> speciesRegistry = new HashMap<>();

    public static void registerSpecies(String speciesName, List<SpeciesFeature> speciesData, List<GeneData> geneData)
    {
        speciesRegistry.put(speciesName, new SpeciesRegistryObject(speciesName, speciesData, geneData));
    }
    public static Genome getSpeciesGenome(String speciesName)
    {
        return new Genome(speciesRegistry.get(speciesName).geneData);
    }
    public static List<SpeciesFeature> getSpeciesData(String speciesName)
    {
        return speciesRegistry.get(speciesName).speciesData;
    }
    public static SpeciesFeature getSpeciesFeature(String speciesName, SpeciesFeature.FeatureTypes featureType)
    {
        return speciesRegistry.get(speciesName).speciesDataMap.get(featureType);
    }
}
