package com.reuxertz.genesisAPI;

import com.reuxertz.genesisAPI.registry.IGenesisRegistry;

public interface IGenesisPlugin {

    void register(IGenesisRegistry registry);

    String getModID();
}
