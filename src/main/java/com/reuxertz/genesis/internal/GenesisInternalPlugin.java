package com.reuxertz.genesis.internal;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.api.GenesisPlugin;
import com.reuxertz.genesis.api.IGenesisPlugin;
import com.reuxertz.genesis.api.IGenesisRegistry;
import com.reuxertz.genesis.entities.EntityHuman;
import net.minecraft.block.material.MapColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

@GenesisPlugin
public class GenesisInternalPlugin implements IGenesisPlugin {

    @Override
    public void register(IGenesisRegistry registry) {

        EntityEntry registree = EntityEntryBuilder.create()
                .entity(EntityHuman.class)
                .id(new ResourceLocation(Genesis.MODID, "human"), 0)
                .name("human")
                .tracker(80, 3, false)
                .egg(MapColor.BLUE.colorValue, MapColor.YELLOW.colorValue)
                //.spawn(EnumCreatureType.CREATURE, 20, 1, 5, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST))
                .build();

        registry.registerEntity("human", registree);

	    return;
    }
    
    @Override
    public String getModID() {
	return Genesis.MODID;
    }


}
