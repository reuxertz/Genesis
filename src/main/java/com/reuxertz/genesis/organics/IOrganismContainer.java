package com.reuxertz.genesis.organics;

import com.reuxertz.genesis.registry.RegistryObject;
import net.minecraft.world.World;

public interface IOrganismContainer {

    String getOrganismName();

    RegistryObject getRegistryObject();

    World getWorld();

    Organism getOrganism();

    void handleGrowth();

    boolean handleReproduction();

    void refreshState();
}
