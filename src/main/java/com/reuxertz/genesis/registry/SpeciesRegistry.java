package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.organics.GeneHelper;
import com.reuxertz.genesis.organics.Genome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpeciesRegistry {

    private static class SpeciesRegistryObject
    {
        public List<SpeciesFeature> speciesData;
        public Map<SpeciesFeature.FeatureTypes, SpeciesFeature> speciesDataMap = new HashMap<>();
        public List<GeneData> geneData = new ArrayList<>();
        public String speciesName;

        public SpeciesRegistryObject(String speciesName, List<SpeciesFeature> speciesData, List<GeneData> geneData)
        {
            this.speciesData = speciesData;
            this.speciesName = speciesName;

            for (int i = 0; i < speciesData.size(); i++)
                speciesDataMap.put(speciesData.get(i).featureType, speciesData.get(i));

            this.geneData = geneData;
        }
    }
    private static class BreedRegistryObject2
    {
        public List<GeneData> geneData;
        public Map<String, GeneData> breedDataMap = new HashMap<>();
        public String speciesName;

        public BreedRegistryObject2(String speciesName, String breedName, List<GeneData> geneData)
        {
            this.geneData = geneData;
            this.speciesName = speciesName;

            for (int i = 0; i < geneData.size(); i++)
                breedDataMap.put(speciesName + "_" + breedName, geneData.get(i));
        }
    }

    private static final HashMap<String, SpeciesRegistryObject> speciesRegistry = new HashMap<>();
    //private static final HashMap<String, BreedRegistryObject> breedRegistry = new HashMap<>();

//    public static void registerBreed(String speciesName, String breedName, List<GeneData> geneData)
//    {
//        if (!(breedName == null || breedName == ""))
//            speciesName = speciesName + "_" + breedName;
//
//        breedRegistry.put(speciesName, new BreedRegistryObject(speciesName, breedName, geneData));
//    }
    public static void registerSpecies(String speciesName, List<SpeciesFeature> speciesData, List<GeneData> geneData)
    {
        speciesRegistry.put(speciesName, new SpeciesRegistryObject(speciesName, speciesData, geneData));
    }
    public static Genome getSpeciesGenome(String speciesName)
    {
        return new Genome(speciesRegistry.get(speciesName).geneData);
    }
    public static SpeciesFeature getSpeciesFeature(String speciesName, SpeciesFeature.FeatureTypes featureType)
    {
        return speciesRegistry.get(speciesName).speciesDataMap.get(featureType);
    }
}
