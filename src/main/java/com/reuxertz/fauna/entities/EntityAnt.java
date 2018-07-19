package com.reuxertz.fauna.entities;

import com.reuxertz.genesis.entities.EntityGenesisAnimal;
import com.reuxertz.genesisAPI.registry.RegistryObject;
import net.minecraft.world.World;

public class EntityAnt extends EntityGenesisAnimal {

    public String getRegistryName() { return "ant"; }

    public EntityAnt(World world) {
        this(world, null, null, 0d, 0d);
    }
    public EntityAnt(World world, RegistryObject registryObject, String subspecies, Double age, Double mass)
    {
        super(world, registryObject, subspecies, age, mass);


    }


}
