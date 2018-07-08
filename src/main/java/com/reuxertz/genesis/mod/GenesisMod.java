package com.reuxertz.genesis.mod;

import com.reuxertz.fauna.Fauna;
import com.reuxertz.genesis.api.GenesisPlugin;
import com.reuxertz.genesis.api.IGenesisPlugin;
import com.reuxertz.genesis.api.IGenesisRegistry;
import com.reuxertz.genesis.api.blocks.BlockContainerBase;
import com.reuxertz.genesis.api.items.ItemContainerBase;
import com.reuxertz.genesis.api.tileentities.TileEntityContainerBase;
import com.reuxertz.genesis.items.EntitySpawnEgg;

@GenesisPlugin
public class GenesisMod implements IGenesisPlugin {

    @Override
    public void register(IGenesisRegistry registry) {

        registry.registerItem("entity_spawn_egg", Fauna.MODID, new EntitySpawnEgg()).autoRegister();

        registry.registerBlock("block_table_test", Genesis.MODID, new BlockContainerBase("block_table_test"), TileEntityContainerBase.class).autoRegister();

        registry.registerItem("item_sack", Genesis.MODID, new ItemContainerBase("item_sack")).autoRegister();

	    return;
    }
    
    @Override
    public String getModID() {
	    return Genesis.MODID;
    }


}
