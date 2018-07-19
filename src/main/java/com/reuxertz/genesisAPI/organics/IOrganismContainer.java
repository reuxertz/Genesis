package com.reuxertz.genesisAPI.organics;

import com.reuxertz.genesisAPI.registry.RegistryObject;
import net.minecraft.world.World;

public interface IOrganismContainer {

    RegistryObject getRegistryObject();

    World getWorld();

    Organism getOrganism();

    void handleGrowth();

    boolean handleReproduction();

    void refreshState();
}
