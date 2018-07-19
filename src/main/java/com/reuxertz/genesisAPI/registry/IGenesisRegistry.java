package com.reuxertz.genesisAPI.registry;

import com.reuxertz.genesisAPI.organics.Organism;
import com.reuxertz.genesisAPI.organics.GeneData;
import com.reuxertz.genesisAPI.organics.SpeciesFeature;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.EntityEntry;

import java.util.List;
import java.util.function.Consumer;

public interface IGenesisRegistry {

    RegistryObject registerItem(String name, String modId, Item item);
    RegistryObject registerBlock(String name, String modId, Block block);
    RegistryObject registerBlock(String name, String modId, Block block, Class tileEntityClass);
    RegistryObject registerEntity(String name, String modId, EntityEntry entityEntry);
    RegistryObject registerEntity(String name, String modId, EntityEntry entityEntry, ModelBase modelBase);

    void registerEventHandler(String name, IEventHandler eventHandler);

    void registerBreed(String name, List<GeneData> genes);
    void registerBreed(String name, String subspecies, List<GeneData> genes);
    void registerSpecies(String name, List<SpeciesFeature> speciesData);
    void registerSpeciesState(String name, String speciesState);
    void registerSpeciesState(String name, String speciesState, Consumer<Organism> stateConstructor);

}
