package com.reuxertz.fauna.entities;

import com.reuxertz.genesis.entities.EntityGenesisAnimal;
import com.reuxertz.genesis.registry.RegistryObject;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityHuman extends EntityGenesisAnimal {

    public String getRegistryName() { return "human"; }

    public EntityHuman(World world) {
        this(world, null, null, 0d, 0d);
    }
    public EntityHuman(World world, RegistryObject registryObject, String subspecies, Double age, Double mass)
    {
        super(world, registryObject, subspecies, age, mass);

        this.tasks.addTask(0, new EntityAISwimming(this));
//        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
//        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityEvoker.class, 12.0F, 0.8D, 0.8D));
//        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityVindicator.class, 8.0F, 0.8D, 0.8D));
//        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityVex.class, 8.0F, 0.6D, 0.6D));
        //this.tasks.addTask(1, new EntityAITradePlayer(this));
        //this.tasks.addTask(1, new EntityAILookAtTradePlayer(this));
//        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
//        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
//        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
//        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
        //this.tasks.addTask(6, new EntityAIVillagerMate(this));
        //this.tasks.addTask(7, new EntityAIFollowGolem(this));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        //this.tasks.addTask(9, new EntityAIVillagerInteract(this));
        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));

    }


}
