package com.reuxertz.genesisAPI;

import com.reuxertz.genesisAPI.internal.IGenesisRegistry;

public interface IGenesisPlugin {

    void register(IGenesisRegistry registry);

    String getModID();
}
