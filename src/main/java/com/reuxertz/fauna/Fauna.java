package com.reuxertz.fauna;

import com.reuxertz.fauna.entities.*;
import com.reuxertz.fauna.items.EntitySpawnEgg;
import com.reuxertz.fauna.proxy.ServerProxy;
import com.reuxertz.genesis.api.GenesisPlugin;
import com.reuxertz.genesis.api.IGenesisPlugin;
import com.reuxertz.genesis.api.IGenesisRegistry;
import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.entities.EntityAnt;
import com.reuxertz.genesis.entities.EntityHuman;
import com.reuxertz.genesis.handlers.ForgeHandler;
import com.reuxertz.genesis.handlers.NetworkHandler;
import com.reuxertz.genesis.mod.Genesis;
import com.reuxertz.genesis.mod.GenesisApiHandler;
import com.reuxertz.genesis.proxy.CommonProxy;
import com.reuxertz.genesis.registry.GenesisRegistry;
import com.reuxertz.genesis.util.IDHelper;
import com.reuxertz.genesis.util.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

import java.util.Arrays;

@Mod(modid = Fauna.MODID, name = Fauna.NAME, version = Fauna.VERSION, dependencies = "required-after:forge@[14.23.3.2655,)", useMetadata = true)
@Mod.EventBusSubscriber(modid = Fauna.MODID)
public class Fauna implements IGenesisPlugin
{
    public static final String MODID = "fauna";
    public static final String NAME = "Fauna";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "com.reuxertz.fauna.proxy.ClientProxy", serverSide = "com.reuxertz.fauna.proxy.ServerProxy")
    public static ServerProxy proxy;

    @GenesisPlugin
    public Fauna()
    {
        register(Genesis.registry);
        return;
    }

    public String getModID() { return Fauna.MODID; }
    public void register(IGenesisRegistry registry)
    {
        registry.registerItem("entity_spawn_egg", Fauna.MODID, new EntitySpawnEgg()).autoRegister();

        registry.registerEntity("human", Fauna.MODID, EntityEntryBuilder.create()
                .entity(EntityHuman.class)
                .id(new ResourceLocation(Fauna.MODID, "human"), IDHelper.getNextID("fauna"))
                .name("human")
                .tracker(80, 3, false)
                //.egg(MapColor.BROWN.colorValue, MapColor.GOLD.colorValue)
                //.spawn(EnumCreatureType.CREATURE, 20, 1, 5, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST))
                .build(), new ModelPlayer(1.0f, false))
                .autoRegister()
                .registerOverlay("human", "skin", 0)
                .registerOverlay("human", "eyes", 1)
                .registerOverlay("human", "eyes_white", 1)
                .registerOverlay("human", "hair", 1)
                .registerOverlay("human", "mouth", 1)
                .registerSpecies("human",
                    Arrays.asList(
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultMass, 70000),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultAgeTicks, TimeHelper.ConvertYearsToTicks(20)),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.NewbornMass, 3500),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.ClutchSize, 1.01),

                        new SpeciesFeature(SpeciesFeature.FeatureTypes.SkinLayer, 1),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.HairLayer, 1),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.EyeLayer, 1),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.MouthLayer, 1)))
                .registerBreed("human", "caucasian",
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
                    .registerBreed("human", "negroid",
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
                    .registerBreed("human", "mongoloid",
                    Arrays.asList(
                        new GeneData(GeneData.GeneType.AdultMassFactor, 0, 0),
                        new GeneData(GeneData.GeneType.GrowthFactor, 0, 0),
                        new GeneData(GeneData.GeneType.NewBornMassFactor, 0, 0),
                        new GeneData(GeneData.GeneType.ClutchSizeFactor, 0, 0),

                        new GeneData(GeneData.GeneType.SkinLayer, 1, .65, .7, .5),
                        new GeneData(GeneData.GeneType.HairLayer, 1, .5, .5, .4),
                        new GeneData(GeneData.GeneType.EyesLayer, 1, .4, .2, 0),
                        new GeneData(GeneData.GeneType.MouthLayer, 1, 1, 1, 1)
                    ));

        registry.registerEntity("ant", Fauna.MODID, EntityEntryBuilder.create()
                .entity(EntityAnt.class)
                .id(new ResourceLocation(Fauna.MODID, "ant"), IDHelper.getNextID("fauna"))
                .name("ant")
                .tracker(80, 3, false)
                //.egg(MapColor.BROWN.colorValue, MapColor.GOLD.colorValue)
                //.spawn(EnumCreatureType.CREATURE, 20, 1, 5, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST))
                .build(), new ModelAnt())
                .autoRegister()
                .registerSpecies("ant",
                    Arrays.asList(
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultMass, 20),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultAgeTicks, TimeHelper.ConvertYearsToTicks(20)),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.NewbornMass, 5),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.ClutchSize, 0),

                        new SpeciesFeature(SpeciesFeature.FeatureTypes.SkinLayer, 1),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.HairLayer, 1),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.EyeLayer, 1),
                        new SpeciesFeature(SpeciesFeature.FeatureTypes.MouthLayer, 1)))
                .registerBreed("ant", "wood",
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
                .registerOverlay("ant", "ant_worker", 0)
                .registerOverlay("ant", "ant_worker_eyes", 1)
        ;

        //registry.registerEntity("human", registree);

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

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        OBJLoader.INSTANCE.addDomain(MODID);
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}