package com.reuxertz.genesis.api.entities;

import com.reuxertz.genesis.entities.EntityOrganism;
import com.reuxertz.genesis.organics.IOrganismContainer;
import com.reuxertz.genesis.organics.Organism;
import net.minecraft.world.World;

public abstract class EntityGenesisAnimal extends EntityOrganism {

    public EntityGenesisAnimal(World world)
    {
        super(world);
    }
}
