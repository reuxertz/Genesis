package com.reuxertz.genesis.internal;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.api.GenesisPlugin;
import com.reuxertz.genesis.api.IGenesisPlugin;
import com.reuxertz.genesis.api.IGenesisRegistry;
import com.reuxertz.genesis.api.items.EntitySpawnEgg;

@GenesisPlugin
public class GenesisInternalPlugin implements IGenesisPlugin {

    @Override
    public void register(IGenesisRegistry registry) {

        registry.registerItem("entity_spawn_egg", new EntitySpawnEgg());

	    return;
    }
    
    @Override
    public String getModID() {
	return Genesis.MODID;
    }


}
