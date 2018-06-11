package com.reuxertz.genesis.entities;

import com.reuxertz.genesis.api.entities.EntityGenesisAnimal;
import com.reuxertz.genesis.organics.Organism;
import net.minecraft.world.World;

public class EntityHuman extends EntityGenesisAnimal {

    public String getName() { return "human"; }

    public EntityHuman(World world)
    {
        super(world);
    }


}
