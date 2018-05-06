package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.block.SimpleBlock;
import com.reuxertz.genesis.block.SimpleBlockOre;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class BlockRegistry {

    @GameRegistry.ObjectHolder("genesis:simpleblock2")
    public static SimpleBlock simpleBlock2;
    @GameRegistry.ObjectHolder("genesis:simpleblock3")
    public static SimpleBlockOre simpleBlock3;
    @GameRegistry.ObjectHolder("genesis:ore_copper")
    public static SimpleBlockOre oreCopper;
    @GameRegistry.ObjectHolder("genesis:crop_garlic")
    public static SimpleBlockOre cropGarlic;

}
