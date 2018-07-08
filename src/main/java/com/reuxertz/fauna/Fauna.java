package com.reuxertz.fauna;

import com.reuxertz.fauna.entities.EntityCrab;
import com.reuxertz.fauna.entities.models.ModelCrab;
import com.reuxertz.genesis.api.GenesisPlugin;
import com.reuxertz.genesis.items.EntitySpawnEgg;
import com.reuxertz.fauna.entities.models.ModelAnt;
import com.reuxertz.genesis.api.IGenesisPlugin;
import com.reuxertz.genesis.api.IGenesisRegistry;
import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.fauna.entities.EntityAnt;
import com.reuxertz.fauna.entities.EntityHuman;
import com.reuxertz.genesis.mod.Genesis;
import com.reuxertz.genesis.registry.GenesisRegistry;
import com.reuxertz.genesis.util.IDHelper;
import com.reuxertz.genesis.util.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

import java.awt.*;
import java.util.Arrays;

@Mod(modid = Fauna.MODID, name = Fauna.NAME, version = Fauna.VERSION, dependencies = "required-after:forge@[14.23.3.2655,)", useMetadata = true)
@Mod.EventBusSubscriber(modid = Fauna.MODID)
public class Fauna implements IGenesisPlugin
{
    public static final String MODID = "fauna";
    public static final String NAME = "Fauna";
    public static final String VERSION = "1.0";

    @GenesisPlugin
    public Fauna()
    {
        OBJLoader.INSTANCE.addDomain(MODID);
    }

    public String getModID() { return Fauna.MODID; }
    public void register(IGenesisRegistry registry)
    {
        registry.registerEntity("human", Fauna.MODID, EntityEntryBuilder.create()
                .entity(EntityHuman.class)
                .id(new ResourceLocation(Fauna.MODID, "human"), IDHelper.getNextID("fauna_animal"))
                .name("human")
                .tracker(80, 3, false)
                .build(), new ModelPlayer(1.0f, false))
                //.autoRegister()
                .registerSpawnEggColor(new Color(102, 51, 0).getRGB(), new Color(255, 204, 0).getRGB())
                .registerOverlay("skin", 0)
                .registerOverlay("eyes", 1)
                .registerOverlay("eyes_white", 1)
                .registerOverlay("hair", 1)
                .registerOverlay("mouth", 1)
                .registerSpecies(
                    Arrays.asList(
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultMass, 70000),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultAgeTicks, TimeHelper.ConvertYearsToTicks(20)),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.NewbornMass, 3500),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.ClutchSize, 1.01),

                        new SpeciesFeature(SpeciesFeature.FeatureTypes.SkinLayer, 1),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.HairLayer, 1),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.EyeLayer, 1),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.MouthLayer, 1)))
                .registerBreed("caucasian",
                    Arrays.asList(
                        new GeneData(GeneData.GeneType.AdultMassFactor, 0, 0),
                        new GeneData(GeneData.GeneType.GrowthFactor, 0, 0),
                        new GeneData(GeneData.GeneType.NewBornMassFactor, 0, 0),
                        new GeneData(GeneData.GeneType.ClutchSizeFactor, 0, 0),

                        new GeneData(GeneData.GeneType.SkinLayer, 0, 1, 1, 1),
                        new GeneData(GeneData.GeneType.HairLayer, 1, 1, 1, 0),
                        new GeneData(GeneData.GeneType.EyesLayer, 1, .2, .7, 1),
                        new GeneData(GeneData.GeneType.MouthLayer, 1, 1, 1, 1)
                    ))
                .registerBreed("negroid",
                    Arrays.asList(
                        new GeneData(GeneData.GeneType.AdultMassFactor, 0, 0),
                        new GeneData(GeneData.GeneType.GrowthFactor, 0, 0),
                        new GeneData(GeneData.GeneType.NewBornMassFactor, 0, 0),
                        new GeneData(GeneData.GeneType.ClutchSizeFactor, 0, 0),

                        new GeneData(GeneData.GeneType.SkinLayer, 1, .3, .3, .2),
                        new GeneData(GeneData.GeneType.HairLayer, 1, .2, .2, .1),
                        new GeneData(GeneData.GeneType.EyesLayer, 1, .3, .2, .2),
                        new GeneData(GeneData.GeneType.MouthLayer, 1, 1, 1, 1)
                    ))
                .registerBreed("mongoloid",
                    Arrays.asList(
                        new GeneData(GeneData.GeneType.AdultMassFactor, 0, 0),
                        new GeneData(GeneData.GeneType.GrowthFactor, 0, 0),
                        new GeneData(GeneData.GeneType.NewBornMassFactor, 0, 0),
                        new GeneData(GeneData.GeneType.ClutchSizeFactor, 0, 0),

                        new GeneData(GeneData.GeneType.SkinLayer, 1, .65, .7, .5),
                        new GeneData(GeneData.GeneType.HairLayer, 1, .5, .5, .4),
                        new GeneData(GeneData.GeneType.EyesLayer, 1, .4, .2, 0),
                        new GeneData(GeneData.GeneType.MouthLayer, 1, 1, 1, 1)
                    ))
                .registerSpeciesState("male")
                .registerSpeciesState("female");

        registry.registerEntity("ant", Fauna.MODID, EntityEntryBuilder.create()
                .entity(EntityAnt.class)
                .id(new ResourceLocation(Fauna.MODID, "ant"), IDHelper.getNextID("fauna_animal"))
                .name("ant")
                .tracker(80, 3, false)
                .build(), new ModelAnt())
                .autoRegister()
                .registerSpecies(
                    Arrays.asList(
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultMass, 20),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultAgeTicks, TimeHelper.ConvertYearsToTicks(20)),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.NewbornMass, 5),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.ClutchSize, 0)
                    ))
                .registerBreed("wood",
                    Arrays.asList(
                        new GeneData(GeneData.GeneType.AdultMassFactor, 0, 0),
                        new GeneData(GeneData.GeneType.GrowthFactor, 0, 0),
                        new GeneData(GeneData.GeneType.NewBornMassFactor, 0, 0),
                        new GeneData(GeneData.GeneType.ClutchSizeFactor, 0, 0)
                        ))
                .registerOverlay("worker", 0)
                .registerOverlay("eyes", 1)
                .registerOverlay("wings", 1)
                .registerSpeciesState("queen")
                .registerSpeciesState("male")
                .registerSpeciesState("worker")
                .registerSpeciesState("soldier");
        ;

        registry.registerEntity("crab", Fauna.MODID, EntityEntryBuilder.create()
                .entity(EntityCrab.class)
                .id(new ResourceLocation(Fauna.MODID, "crab"), IDHelper.getNextID("fauna_animal"))
                .name("crab")
                .tracker(80, 3, false)
                .build(), new ModelCrab())
                .autoRegister()
                .registerSpecies(
                        Arrays.asList(
                            new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultMass, 20),
                            new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultAgeTicks, TimeHelper.ConvertYearsToTicks(20)),
                            new SpeciesFeature(SpeciesFeature.FeatureTypes.NewbornMass, 5),
                            new SpeciesFeature(SpeciesFeature.FeatureTypes.ClutchSize, 0)
                        ))
                .registerBreed("brown",
                        Arrays.asList(
                            new GeneData(GeneData.GeneType.AdultMassFactor, 0, 0),
                            new GeneData(GeneData.GeneType.GrowthFactor, 0, 0),
                            new GeneData(GeneData.GeneType.NewBornMassFactor, 0, 0),
                            new GeneData(GeneData.GeneType.ClutchSizeFactor, 0, 0)
                        ))
                .registerSpeciesState("male")
                .registerSpeciesState("female");
        ;




        return;
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {

        GenesisRegistry.registerModEntities(event, Fauna.MODID);

        return;
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        GenesisRegistry.registerModBlocks(event, Fauna.MODID);

        return;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        GenesisRegistry.registerModItems(event, Fauna.MODID);

        return;
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {

        GenesisRegistry.registerEntityRenderers(Fauna.MODID);
        GenesisRegistry.initModels(Fauna.MODID);
    }
}