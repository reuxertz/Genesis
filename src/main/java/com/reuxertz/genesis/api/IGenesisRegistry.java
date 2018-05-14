package com.reuxertz.genesis.api;

import com.reuxertz.genesis.api.genes.GeneData;
import com.reuxertz.genesis.registry.RegistryObject;

import java.util.List;

public interface IGenesisRegistry {

    //Base
    IGenesisRegistry registerContent(RegistryObject registryObject);

    //Metal/Ore registers
    IGenesisRegistry registerOre(String name);
    IGenesisRegistry registerNugget(String name);
    IGenesisRegistry registerIngot(String name);
    IGenesisRegistry registerMetalBlock(String name);
    IGenesisRegistry registerMetal(String name);
    IGenesisRegistry registerMetal(String name, boolean enableRaw, boolean hasNuggetIngot, boolean enableArmorSet);

    //Plant registers
    IGenesisRegistry registerCrop(String name);

    //Ecosystem registers
    IGenesisRegistry registerSpecies(String name, List<GeneData> genes);
}
