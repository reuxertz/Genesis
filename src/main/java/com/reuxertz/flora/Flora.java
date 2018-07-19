package com.reuxertz.flora;

import com.reuxertz.fauna.Fauna;
import com.reuxertz.genesisAPI.*;
import com.reuxertz.genesis.registry.MultiRegistryHelper;
import com.reuxertz.genesisAPI.registry.IGenesisRegistry;
import com.reuxertz.genesisAPI.organics.GeneData;
import com.reuxertz.genesisAPI.organics.SpeciesFeature;

import java.util.Arrays;

//@Mod(modid = Flora.MODID, name = Flora.NAME, version = Flora.VERSION, dependencies = "required-after:forge@[14.23.3.2655,)", useMetadata = true)
public class Flora implements IGenesisPlugin
{
    public static final String MODID = "flora";
    public static final String NAME = "Flora";
    public static final String VERSION = "1.0";

    @GenesisPlugin
    public Flora()
    {
        return;
    }

    public String getModID() { return Fauna.MODID; }
    public void register(IGenesisRegistry registry)
    {
        MultiRegistryHelper.registerCrop("onion", Flora.MODID)
                .autoRegister()
                .registerSpecies(
                        Arrays.asList(
                            new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultMass, 2000),
                            new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultAgeTicks, 0),
                            new SpeciesFeature(SpeciesFeature.FeatureTypes.NewbornMass, 10),
                            new SpeciesFeature(SpeciesFeature.FeatureTypes.ClutchSize, 1.5)))
                .registerBreed("",
                        Arrays.asList(
                            new GeneData(GeneData.GeneType.AdultMassFactor, 0, 0),
                            new GeneData(GeneData.GeneType.GrowthFactor, 0, 0),
                            new GeneData(GeneData.GeneType.NewBornMassFactor, 0, 0),
                            new GeneData(GeneData.GeneType.ClutchSizeFactor, 0, 0)));
        return;
    }
}