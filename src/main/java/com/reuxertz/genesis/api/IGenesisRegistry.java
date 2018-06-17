package com.reuxertz.genesis.api;

import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.render.LayerGenesisLiving;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;

import java.util.List;
import java.util.Map;

public interface IGenesisRegistry {

    IGenesisRegistry registerItem(String name);
    IGenesisRegistry registerItem(String name, Item item);

    //Metal/Ore registers
    IGenesisRegistry registerOre(String name);
    IGenesisRegistry registerNugget(String name);
    IGenesisRegistry registerIngot(String name);
    IGenesisRegistry registerMetalBlock(String name);
    IGenesisRegistry registerMetal(String name);
    IGenesisRegistry registerMetal(String name, boolean isAlloy, boolean hasNuggetIngot, boolean enableArmorSet);

    //Plant registers
    IGenesisRegistry registerCrop(String name);

    //Entity registers
    IGenesisRegistry registerEntity(String name, EntityEntry entityEntry, ModelBase modelBase);
    IGenesisRegistry registerOverlay(String name, String overlayName, int zIndex);

    //Ecosystem registers
//    IGenesisRegistry registerBreed(String name, List<GeneData> genes);
//    IGenesisRegistry registerBreed(String name, String subspecies, List<GeneData> genes);
//    IGenesisRegistry registerSpecies(String name, List<SpeciesFeature> speciesData);
    IGenesisRegistry registerSpecies(String name, List<SpeciesFeature> speciesData, List<GeneData> genes);

}
