package com.reuxertz.fauna;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.api.GenesisPlugin;
import com.reuxertz.genesis.api.IGenesisPlugin;
import com.reuxertz.genesis.api.IGenesisRegistry;
import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.entities.EntityHuman;
import com.reuxertz.genesis.render.LayerGenesisLiving;
import com.reuxertz.genesis.render.RenderGenesisLiving;
import net.minecraft.block.material.MapColor;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

import javax.annotation.Nullable;
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
        registry.registerEntity("human", EntityEntryBuilder.create()
                .entity(EntityHuman.class)
                .id(new ResourceLocation(Fauna.MODID, "human"), 0)
                .name("human")
                .tracker(80, 3, false)
                .egg(MapColor.BROWN.colorValue, MapColor.GOLD.colorValue)
                //.spawn(EnumCreatureType.CREATURE, 20, 1, 5, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST))
                .build())
                .registerSpecies("human",
                        Arrays.asList(
                                new SpeciesFeature(SpeciesFeature.FeatureTypes.AdultMass, 70000),
                                new SpeciesFeature(SpeciesFeature.FeatureTypes.NewbornMass, 3500),
                                new SpeciesFeature(SpeciesFeature.FeatureTypes.ClutchSize, 1.01)),
                        Arrays.asList(
                                new GeneData(GeneData.GeneType.AdultMassFactor, 0, 0),
                                new GeneData(GeneData.GeneType.GrowthFactor, 0, 0),
                                new GeneData(GeneData.GeneType.NewBornMassFactor, 0, 0),
                                new GeneData(GeneData.GeneType.ClutchSizeFactor, 0, 0)))
                .registerOverlay("human", "eyes")
                .registerOverlay("human", "hair")
                .registerOverlay("human", "mouth");

        //registry.registerEntity("human", registree);

        return;
    }
}