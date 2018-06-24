package com.reuxertz.genesis.api;

import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.registry.RegistryObject;
import com.reuxertz.genesis.render.LayerGenesisLiving;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;

import java.util.List;
import java.util.Map;

public interface IGenesisRegistry {

    RegistryObject registerItem(String name, Item item);
    RegistryObject registerBlock(String name, Block block);
    RegistryObject registerEntity(String name, EntityEntry entityEntry, ModelBase modelBase);

    void registerEventHandler(String name, IEventHandler eventHandler);

    //Ecosystem registers
    void registerBreed(String name, List<GeneData> genes);
    void registerBreed(String name, String subspecies, List<GeneData> genes);
    void registerSpecies(String name, List<SpeciesFeature> speciesData);

}
