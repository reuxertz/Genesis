package com.reuxertz.genesis.organics;

import net.minecraft.world.World;

public interface IOrganismContainer {

    World getWorld();

    Organism getOrganism();

    void handleGrowth();

    boolean handleReproduction();

    void refreshState();
}
