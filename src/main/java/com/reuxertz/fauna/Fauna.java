package com.reuxertz.fauna;

import com.reuxertz.genesis.api.GenesisPlugin;
import com.reuxertz.genesis.api.IGenesisPlugin;
import com.reuxertz.genesis.api.IGenesisRegistry;
import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.entities.EntityAnt;
import com.reuxertz.genesis.entities.EntityHuman;
import com.reuxertz.genesis.util.TimeHelper;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

import java.util.Arrays;

@Mod(modid = Fauna.MODID, name = Fauna.NAME, version = Fauna.VERSION, dependencies = "required-after:forge@[14.23.3.2655,)", useMetadata = true)
public class Fauna implements IGenesisPlugin
{
    public static final String MODID = "fauna";
    public static final String NAME = "Fauna";
    public static final String VERSION = "1.0";

    @GenesisPlugin
    public Fauna()
    {
        return;
    }

    public String getModID() { return Fauna.MODID; }
    public void register(IGenesisRegistry registry)
    {
//        registry.registerEntity("human", EntityEntryBuilder.create()
//                .entity(EntityHuman.class)
//                .id(new ResourceLocation(Fauna.MODID, "human"), 0)
//                .name("human")
//                .tracker(80, 3, false)
//                //.egg(MapColor.BROWN.colorValue, MapColor.GOLD.colorValue)
//                //.spawn(EnumCreatureType.CREATURE, 20, 1, 5, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST))
//                .build(), new ModelPlayer(1.0f, false))
//                .autoRegister()
//                .registerOverlay("human", "skin", 0)
//                .registerOverlay("human", "eyes", 1)
//                .registerOverlay("human", "eyes_white", 1)
//                .registerOverlay("human", "hair", 1)
//                .registerOverlay("human", "mouth", 1)
//                .registerSpecies("human",
//                    Arrays.asList(
//                        new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultMass, 70000),
//                        new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultAgeTicks, TimeHelper.ConvertYearsToTicks(20)),
//                        new SpeciesFeature(SpeciesFeature.FeatureTypes.NewbornMass, 3500),
//                        new SpeciesFeature(SpeciesFeature.FeatureTypes.ClutchSize, 1.01),
//
//                        new SpeciesFeature(SpeciesFeature.FeatureTypes.SkinLayer, 1),
//                        new SpeciesFeature(SpeciesFeature.FeatureTypes.HairLayer, 1),
//                        new SpeciesFeature(SpeciesFeature.FeatureTypes.EyeLayer, 1),
//                        new SpeciesFeature(SpeciesFeature.FeatureTypes.MouthLayer, 1)))
//                .registerBreed("human", "caucasian",
//                    Arrays.asList(
//                        new GeneData(GeneData.GeneType.AdultMassFactor, 0, 0),
//                        new GeneData(GeneData.GeneType.GrowthFactor, 0, 0),
//                        new GeneData(GeneData.GeneType.NewBornMassFactor, 0, 0),
//                        new GeneData(GeneData.GeneType.ClutchSizeFactor, 0, 0),
//
//                        new GeneData(GeneData.GeneType.SkinLayer, 0, 1, 1, 1),
//                        new GeneData(GeneData.GeneType.HairLayer, 1, 1, 1, 0),
//                        new GeneData(GeneData.GeneType.EyesLayer, 1, .2, .7, 1),
//                        new GeneData(GeneData.GeneType.MouthLayer, 1, 1, 1, 1)
//                    ))
//                    .registerBreed("human", "negroid",
//                    Arrays.asList(
//                        new GeneData(GeneData.GeneType.AdultMassFactor, 0, 0),
//                        new GeneData(GeneData.GeneType.GrowthFactor, 0, 0),
//                        new GeneData(GeneData.GeneType.NewBornMassFactor, 0, 0),
//                        new GeneData(GeneData.GeneType.ClutchSizeFactor, 0, 0),
//
//                        new GeneData(GeneData.GeneType.SkinLayer, 1, .3, .3, .2),
//                        new GeneData(GeneData.GeneType.HairLayer, 1, .2, .2, .1),
//                        new GeneData(GeneData.GeneType.EyesLayer, 1, .3, .2, .2),
//                        new GeneData(GeneData.GeneType.MouthLayer, 1, 1, 1, 1)
//                    ))
//                    .registerBreed("human", "mongoloid",
//                    Arrays.asList(
//                        new GeneData(GeneData.GeneType.AdultMassFactor, 0, 0),
//                        new GeneData(GeneData.GeneType.GrowthFactor, 0, 0),
//                        new GeneData(GeneData.GeneType.NewBornMassFactor, 0, 0),
//                        new GeneData(GeneData.GeneType.ClutchSizeFactor, 0, 0),
//
//                        new GeneData(GeneData.GeneType.SkinLayer, 1, .65, .7, .5),
//                        new GeneData(GeneData.GeneType.HairLayer, 1, .5, .5, .4),
//                        new GeneData(GeneData.GeneType.EyesLayer, 1, .4, .2, 0),
//                        new GeneData(GeneData.GeneType.MouthLayer, 1, 1, 1, 1)
//                    ));
//
//        registry.registerEntity("ant", EntityEntryBuilder.create()
//                .entity(EntityAnt.class)
//                .id(new ResourceLocation(Fauna.MODID, "ant"), 0)
//                .name("ant")
//                .tracker(80, 3, false)
//                //.egg(MapColor.BROWN.colorValue, MapColor.GOLD.colorValue)
//                //.spawn(EnumCreatureType.CREATURE, 20, 1, 5, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST))
//                .build(), new ModelPlayer(1.0f, false))
//                .autoRegister()
//                .registerSpecies("ant",
//                    Arrays.asList(
//                        new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultMass, 70000),
//                        new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultAgeTicks, TimeHelper.ConvertYearsToTicks(20)),
//                        new SpeciesFeature(SpeciesFeature.FeatureTypes.NewbornMass, 3500),
//                        new SpeciesFeature(SpeciesFeature.FeatureTypes.ClutchSize, 1.01),
//
//                        new SpeciesFeature(SpeciesFeature.FeatureTypes.SkinLayer, 1),
//                        new SpeciesFeature(SpeciesFeature.FeatureTypes.HairLayer, 1),
//                        new SpeciesFeature(SpeciesFeature.FeatureTypes.EyeLayer, 1),
//                        new SpeciesFeature(SpeciesFeature.FeatureTypes.MouthLayer, 1)))
//                .registerBreed("ant", "wood",
//                    Arrays.asList(
//                        new GeneData(GeneData.GeneType.AdultMassFactor, 0, 0),
//                        new GeneData(GeneData.GeneType.GrowthFactor, 0, 0),
//                        new GeneData(GeneData.GeneType.NewBornMassFactor, 0, 0),
//                        new GeneData(GeneData.GeneType.ClutchSizeFactor, 0, 0),
//
//                        new GeneData(GeneData.GeneType.SkinLayer, 0, 1, 1, 1),
//                        new GeneData(GeneData.GeneType.HairLayer, 1, 1, 1, 0),
//                        new GeneData(GeneData.GeneType.EyesLayer, 1, .2, .7, 1),
//                        new GeneData(GeneData.GeneType.MouthLayer, 1, 1, 1, 1)
//                        ))
//                .registerOverlay("ant", "ant_worker", 0)
//                .registerOverlay("ant", "ant_worker_eyes", 1)
//        ;

        //registry.registerEntity("human", registree);

        return;
    }
}