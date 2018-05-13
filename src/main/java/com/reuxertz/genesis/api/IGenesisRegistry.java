package com.reuxertz.genesis.api;

import com.reuxertz.genesis.registry.RegistryObject;

public interface IGenesisRegistry {

    //Base
    void registerContent(RegistryObject registryObject);

    //Metal/Ore registers
    void registerOre(String name);
    void registerNugget(String name);
    void registerIngot(String name);
    void registerMetalBlock(String name);
    void registerMetal(String name);
    void registerMetal(String name, boolean enableRaw, boolean hasNuggetIngot, boolean enableArmorSet);

    //Plant registers
    void registerCrop(String name);
}
