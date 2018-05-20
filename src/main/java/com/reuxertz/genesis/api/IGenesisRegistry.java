package com.reuxertz.genesis.api;

import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesData;
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
    IGenesisRegistry registerMetal(String name, boolean isAlloy, boolean hasNuggetIngot, boolean enableArmorSet);

    //Plant registers
    IGenesisRegistry registerCrop(String name);

    //Entity registes
    IGenesisRegistry registerEntity(String name);

    //Ecosystem registers
    IGenesisRegistry registerSpecies(String name, List<SpeciesData> speciesData, List<GeneData> genes);

}
