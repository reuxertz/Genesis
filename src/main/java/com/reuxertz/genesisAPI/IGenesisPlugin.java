package com.reuxertz.genesisAPI;

public interface IGenesisPlugin {

    void register(IGenesisRegistry registry);

    String getModID();
}
