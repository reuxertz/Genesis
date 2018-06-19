package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.organics.GeneHelper;
import com.reuxertz.genesis.organics.Genome;
import it.unimi.dsi.fastutil.Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpeciesRegistry {

    public static class SpeciesRegistryObject
    {
        public List<SpeciesFeature> speciesData;
        public Map<SpeciesFeature.FeatureTypes, SpeciesFeature> speciesDataMap = new HashMap<>();
        public String speciesName;

        public SpeciesRegistryObject(String speciesName, List<SpeciesFeature> speciesData)
        {
            this.speciesData = speciesData;
            this.speciesName = speciesName;

            for (int i = 0; i < speciesData.size(); i++)
                speciesDataMap.put(speciesData.get(i).featureType, speciesData.get(i));
        }
    }
    public static class BreedRegistryObject
    {
        public List<GeneData> geneData;
        public Map<String, List<GeneData>> breedDataMap = new HashMap<>();
        public String speciesName;
        public String breedName;

        public BreedRegistryObject(String speciesName, String breedName, List<GeneData> geneData)
        {
            this.geneData = geneData;
            this.speciesName = speciesName;
            this.breedName = breedName;

            breedDataMap.put(breedName, new ArrayList<>(geneData));
        }
    }

    private static final HashMap<String, SpeciesRegistryObject> speciesRegistry = new HashMap<>();
    private static final HashMap<String, Map<String, BreedRegistryObject>> breedRegistry = new HashMap<>();

    public static ArrayList<BreedRegistryObject> getBreeds(String speciesName)
    {
        if (!breedRegistry.containsKey(speciesName))
            return new ArrayList<>();

        return new ArrayList<>(breedRegistry.get(speciesName).values());
    }

    public static void registerBreed(String speciesName, String breedName, List<GeneData> geneData)
    {
        BreedRegistryObject breedRegistryObject = new BreedRegistryObject(speciesName, breedName, geneData);

        if (!breedRegistry.containsKey(speciesName))
            breedRegistry.put(speciesName, new HashMap<>());

        Map<String, BreedRegistryObject> speciesMap = breedRegistry.get(speciesName);
        if (!speciesMap.containsKey(breedName))
            speciesMap.put(breedName, breedRegistryObject);
    }
    public static void registerSpecies(String speciesName, List<SpeciesFeature> speciesData)
    {
        speciesRegistry.put(speciesName, new SpeciesRegistryObject(speciesName, speciesData));
    }
    public static Genome getSpeciesGenome(String speciesName, String subspecies)
    {
        Map<String, BreedRegistryObject> breedRegistryMap = breedRegistry.get(speciesName);
        BreedRegistryObject breedRegistryObject = breedRegistryMap.get(subspecies);
        return new Genome(breedRegistryObject.geneData);
    }
    public static SpeciesFeature getSpeciesFeature(String speciesName, SpeciesFeature.FeatureTypes featureType)
    {
        return speciesRegistry.get(speciesName).speciesDataMap.get(featureType);
    }
}
