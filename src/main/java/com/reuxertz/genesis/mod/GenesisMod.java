package com.reuxertz.genesis.mod;

import com.reuxertz.genesis.api.GenesisPlugin;
import com.reuxertz.genesis.api.IGenesisPlugin;
import com.reuxertz.genesis.api.IGenesisRegistry;
import com.reuxertz.genesis.items.EntitySpawnEgg;

@GenesisPlugin
public class GenesisMod implements IGenesisPlugin {

    @Override
    public void register(IGenesisRegistry registry) {

        registry.registerItem("entity_spawn_egg", new EntitySpawnEgg()).autoRegister();

	    return;
    }
    
    @Override
    public String getModID() {
	return Genesis.MODID;
    }


}
