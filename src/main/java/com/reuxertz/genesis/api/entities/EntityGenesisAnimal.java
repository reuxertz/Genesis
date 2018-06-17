package com.reuxertz.genesis.api.entities;

import com.reuxertz.genesis.entities.EntityOrganism;
import com.reuxertz.genesis.registry.RegistryObject;
import net.minecraft.world.World;

public abstract class EntityGenesisAnimal extends EntityOrganism {

    public EntityGenesisAnimal(World world, RegistryObject registryObject, String subspecies, Double age, Double mass)
    {
        super(world, registryObject, subspecies, age, mass);
    }
}
