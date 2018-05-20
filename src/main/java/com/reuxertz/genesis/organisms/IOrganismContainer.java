package com.reuxertz.genesis.organisms;

public interface IOrganismContainer {

    Organism getOrganism();

    void handleGrowth();

    void handleDeath();
}
