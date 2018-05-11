package com.reuxertz.genesis.api;

import com.reuxertz.genesis.registry.RegistryObject;

/**
 * The main Registry Interface thats passed into custom classes to be used to register pretty much everything Genesis related.
 * @author Wyn Price
 *
 */
public interface IGenesisRegistry {

    void registerContent(RegistryObject registryObject);

    void registerOre(String name);

    void registerNugget(String name);

    void registerIngot(String name);

    void registerMetalBlock(String name);

    void registerMetal(String name);

    void registerMetal(String name, boolean enableRaw, boolean hasNuggetIngot, boolean enableArmorSet);
}
