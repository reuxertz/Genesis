package com.reuxertz.flora;

import com.reuxertz.genesis.api.GenesisPlugin;
import com.reuxertz.genesis.api.IGenesisPlugin;
import com.reuxertz.genesis.api.IGenesisRegistry;
import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.util.TimeHelper;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;

@Mod(modid = Flora.MODID, name = Flora.NAME, version = Flora.VERSION, dependencies = "required-after:forge@[14.23.3.2655,)", useMetadata = true)
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

    public String getModID() { return Flora.MODID; }
    public void register(IGenesisRegistry registry)
    {
        registry
            .registerCrop("onion")
            .registerSpecies("onion",
                Arrays.asList(
                    new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultMass, 2000),
                    new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultAgeTicks, 0),
                    new SpeciesFeature(SpeciesFeature.FeatureTypes.NewbornMass, 10),
                    new SpeciesFeature(SpeciesFeature.FeatureTypes.ClutchSize, 1.5)),
                //.registerBreed("onion", "",
                Arrays.asList(
                    new GeneData(GeneData.GeneType.AdultMassFactor, 0, 0),
                    new GeneData(GeneData.GeneType.GrowthFactor, 0, 0),
                    new GeneData(GeneData.GeneType.NewBornMassFactor, 0, 0),
                    new GeneData(GeneData.GeneType.ClutchSizeFactor, 0, 0))
            )
        ;

        return;
    }
}