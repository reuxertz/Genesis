package com.reuxertz.genesisAPI.registry;

import com.reuxertz.genesisAPI.GenesisAPI;
import com.reuxertz.genesisAPI.IEventHandler;
import com.reuxertz.genesisAPI.organics.GeneData;
import com.reuxertz.genesisAPI.organics.Organism;
import com.reuxertz.genesisAPI.organics.SpeciesFeature;
import com.reuxertz.genesisAPI.internal.IGenesisRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.EntityEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = GenesisAPI.MODID)
public class GenesisRegistry implements IGenesisRegistry
{
    public static Map<String, RegistryObject> registryObjectHashMap = new HashMap<>();
    public static Map<Item, String> registryItemHashMap = new HashMap<>();
    public static List<RegistryObject> registryObjectList = new ArrayList<>();
    public static List<String> registeredModIds = new ArrayList<>();

    public void iterate(Consumer<RegistryObject> action) {
        registryObjectList.forEach(action);
    }

    public RegistryObject getRegistryObject(String name)
    {
        return registryObjectHashMap.get(name);
    }
    public RegistryObject getRegistryObject(Item item)
    {
        return registryObjectHashMap.get(registryItemHashMap.get(item));
    }

    public GenesisRegistry() {
    }

    public RegistryObject registerContent(RegistryObject registryObject)
    {
        if (registryObjectHashMap.keySet().contains(registryObject.name) ||
            registryObjectHashMap.values().contains(registryObject)  ||
            registryObjectList.contains(registryObject))
            return registryObject;

        registryObjectHashMap.put(registryObject.name, registryObject);
        registryObjectList.add(registryObject);

        if (!registeredModIds.contains(registryObject.modId))
            registeredModIds.add(registryObject.modId);

        if (registryObject.item != null)
            registryItemHashMap.put(registryObject.item, registryObject.name);

        return registryObject;
    }
    public RegistryObject registerItem(String name, String modId, Item item)
    {
        return registerContent(new RegistryObject(GenesisAPI.registry, modId, name, item));
    }
    public RegistryObject registerBlock(String name, String modId, Block block)
    {
        return registerBlock(name, modId, block, null);
    }
    public RegistryObject registerBlock(String name, String modId, Block block, Class tileEntityClass)
    {
        return GenesisAPI.registry.registerContent(new RegistryObject(GenesisAPI.registry, modId, name, block, tileEntityClass));
    }
    //Entities
    public RegistryObject registerEntity(String name, String modId, EntityEntry entityEntry, ModelBase modelBase)
    {
        return registerContent(new RegistryObject(GenesisAPI.registry, modId, name, entityEntry, modelBase));

    }

    //Genetics
    public void registerEventHandler(String name, IEventHandler eventHandler)
    {
        EventRegistry.registerEventHandler(name, eventHandler);
    }
    public void registerBreed(String name, List<GeneData> genes)
    {
        SpeciesRegistry.registerBreed(name, "", genes);
    }
    public void registerBreed(String name, String subspecies, List<GeneData> genes)
    {
        SpeciesRegistry.registerBreed(name, subspecies, genes);
    }
    public void registerSpecies(String name, List<SpeciesFeature> speciesData)
    {
        SpeciesRegistry.registerSpecies(name, speciesData);

    }
    public void registerSpeciesState(String name, String speciesState)
    {
        SpeciesRegistry.registerSpeciesState(name, speciesState, organism -> { });
    }
    public void registerSpeciesState(String name, String speciesState, Consumer<Organism> stateConstructor)
    {
        SpeciesRegistry.registerSpeciesState(name, speciesState, stateConstructor);
    }

}
