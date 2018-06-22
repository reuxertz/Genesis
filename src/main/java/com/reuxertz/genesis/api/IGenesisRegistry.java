package com.reuxertz.genesis.api;

import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.registry.RegistryObject;
import com.reuxertz.genesis.render.LayerGenesisLiving;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;

import java.util.List;
import java.util.Map;

public interface IGenesisRegistry {

    RegistryObject registerItem(String name);
    RegistryObject registerItem(String name, Item item);

    //Metal/Ore registers
    RegistryObject registerOre(String name);
    RegistryObject registerNugget(String name);
    RegistryObject registerIngot(String name);
    RegistryObject registerMetalBlock(String name);
    RegistryObject registerMetal(String name);
    RegistryObject registerMetal(String name, boolean isAlloy, boolean hasNuggetIngot, boolean enableArmorSet);

    //Plant registers
    RegistryObject registerCrop(String name);

    //Entity registers
    RegistryObject registerEntity(String name, EntityEntry entityEntry, ModelBase modelBase);
//    RegistryObject registerOverlay(String name, String overlayName, int zIndex);

    //Ecosystem registers
//    RegistryObject registerBreed(String name, List<GeneData> genes);
//    RegistryObject registerBreed(String name, String subspecies, List<GeneData> genes);
//    RegistryObject registerSpecies(String name, List<SpeciesFeature> speciesData);

}
