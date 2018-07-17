package com.reuxertz.genesis.mod;

import com.reuxertz.genesisAPI.GenesisPlugin;
import com.reuxertz.genesisAPI.IGenesisPlugin;
import com.reuxertz.genesisAPI.IGenesisRegistry;
import com.reuxertz.genesis.block.base.BlockContainerBase;
import com.reuxertz.genesis.items.base.ItemBase;
import com.reuxertz.genesis.items.base.ItemContainerBase;
import com.reuxertz.genesis.tileentities.TileEntityContainerBase;
import com.reuxertz.genesis.block.BlockGenesisGrass;
import com.reuxertz.genesis.items.EntitySpawnEgg;
import net.minecraftforge.client.model.obj.OBJLoader;

public class GenesisMod implements IGenesisPlugin {

    @GenesisPlugin
    public GenesisMod()
    {

    }

    @Override
    public void register(IGenesisRegistry registry) {

        registry.registerItem("entity_spawn_egg", Genesis.MODID, new EntitySpawnEgg()).autoRegister();

        registry.registerBlock("block_table_test", Genesis.MODID, new BlockContainerBase("block_table_test"), TileEntityContainerBase.class).autoRegister();

        registry.registerBlock("genesis_grass", Genesis.MODID, new BlockGenesisGrass("genesis_grass")).autoRegister();

        registry.registerItem("coin", Genesis.MODID, new ItemBase("coin")).autoRegister();
        registry.registerItem("item_sack", Genesis.MODID, new ItemContainerBase("item_sack")).autoRegister();








	    return;
    }
    
    @Override
    public String getModID() {
	    return Genesis.MODID;
    }


}
