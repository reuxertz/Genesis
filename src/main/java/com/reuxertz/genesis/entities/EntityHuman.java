package com.reuxertz.genesis.entities;

import com.reuxertz.genesis.api.entities.EntityGenesisAnimal;
import com.reuxertz.genesis.registry.RegistryObject;
import net.minecraft.world.World;

public class EntityHuman extends EntityGenesisAnimal {

    public String getRegistryName() { return "human"; }

    public EntityHuman(World world) {
        this(world, null, null, 0d, 0d);
    }
    public EntityHuman(World world, RegistryObject registryObject, String subspecies, Double age, Double mass)
    {
        super(world, registryObject, subspecies, age, mass);


    }


}
