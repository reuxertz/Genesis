package com.reuxertz.flora;

import com.reuxertz.fauna.Fauna;
import com.reuxertz.genesis.api.GenesisPlugin;
import com.reuxertz.genesis.api.IGenesisPlugin;
import com.reuxertz.genesis.api.IGenesisRegistry;
import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.registry.GenesisRegistry;
import com.reuxertz.genesis.util.RegistryHelper;
import com.reuxertz.genesis.util.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

import java.util.Arrays;

//@Mod(modid = Flora.MODID, name = Flora.NAME, version = Flora.VERSION, dependencies = "required-after:forge@[14.23.3.2655,)", useMetadata = true)
public class Flora implements IGenesisPlugin
{
    public static final String MODID = "flora";
    public static final String NAME = "Flora";
    public static final String VERSION = "1.0";

    //@GenesisPlugin
    public Flora()
    {
        return;
    }

    public String getModID() { return Flora.MODID; }
    public void register(IGenesisRegistry registry)
    {
        RegistryHelper.registerCrop("onion", getModID())
                .autoRegister()
                .registerSpecies(
                        Arrays.asList(
                            new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultMass, 2000),
                            new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultAgeTicks, 0),
                            new SpeciesFeature(SpeciesFeature.FeatureTypes.NewbornMass, 10),
                            new SpeciesFeature(SpeciesFeature.FeatureTypes.ClutchSize, 1.5)))
                .registerBreed("wild",
                        Arrays.asList(
                            new GeneData(GeneData.GeneType.AdultMassFactor, 0, 0),
                            new GeneData(GeneData.GeneType.GrowthFactor, 0, 0),
                            new GeneData(GeneData.GeneType.NewBornMassFactor, 0, 0),
                            new GeneData(GeneData.GeneType.ClutchSizeFactor, 0, 0)));
        return;
    }
}