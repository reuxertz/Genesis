package com.reuxertz.fauna.entities;

import com.reuxertz.genesis.api.entities.EntityGenesisAnimal;
import com.reuxertz.genesis.registry.RegistryObject;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWaterFlying;
import net.minecraft.world.World;

public class EntityCrow extends EntityGenesisAnimal {
    public EntityCrow(World world) {
        this(world, null, null, 0d, 0d);
    }
    public EntityCrow(World world, RegistryObject registryObject, String subspecies, Double age, Double mass) {
        super(world, registryObject, subspecies, age, mass);

        this.tasks.addTask(2, new EntityAIWanderAvoidWaterFlying(this, 0.1));
    }

    @Override
    public String getRegistryName() {
        return "Crow";
    }
}
