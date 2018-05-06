package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.item.SimpleItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class ItemRegistry {

    //Mod Items
    @GameRegistry.ObjectHolder("genesis:simpleitem")
    public static SimpleItem simpleItem;
    @GameRegistry.ObjectHolder("genesis:simpleitem2")
    public static SimpleItem simpleItem2;
}
