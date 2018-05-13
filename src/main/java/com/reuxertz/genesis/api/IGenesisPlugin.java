package com.reuxertz.genesis.api;

public interface IGenesisPlugin {

    void register(IGenesisRegistry registry);

    String getModID();
}
