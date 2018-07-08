package com.reuxertz.fauna.entities;

import com.reuxertz.genesis.api.entities.EntityGenesisAnimal;
import com.reuxertz.genesis.entities.EntityOrganism;
import com.reuxertz.genesis.registry.RegistryObject;
import net.minecraft.world.World;

public class EntityCrab extends EntityGenesisAnimal {

    public String getRegistryName() { return "crab"; }

    public EntityCrab(World world) {
        this(world, null, null, 0d, 0d);
    }
    public EntityCrab(World world, RegistryObject registryObject, String subspecies, Double age, Double mass)
    {
        super(world, registryObject, subspecies, age, mass);


    }


}
