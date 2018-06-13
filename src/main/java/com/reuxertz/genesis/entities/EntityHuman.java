package com.reuxertz.genesis.entities;

import com.reuxertz.genesis.api.entities.EntityGenesisAnimal;
import net.minecraft.world.World;

public class EntityHuman extends EntityGenesisAnimal {

    public String getOrganismName() { return "human"; }

    public EntityHuman(World world)
    {
        super(world);


    }


}
