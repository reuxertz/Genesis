package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.item.SimpleItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class ItemRegistry {

    //Mod Items
    @GameRegistry.ObjectHolder("genesis:simpleitem")
    public static SimpleItem simpleItem;
    @GameRegistry.ObjectHolder("genesis:simpleitem2")
    public static SimpleItem simpleItem2;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        GenesisRegistry.initModels();
        simpleItem2.initModel();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        GenesisRegistry.registerItems(event);

        //event.getRegistry().register(new SimpleItem().setRegistryName(new SimpleItem().getRegistryName()));
        //event.getRegistry().register(new SimpleItem("simpleItem"));
        event.getRegistry().register(new SimpleItem("simpleItem2"));
//        event.getRegistry().register(new SimpleTexturedItem());
//        event.getRegistry().register(new MultiModelItem());
//
//        event.getRegistry().register(new ItemBlock(ModBlocks.stateTexturedBlock).setRegistryName(ModBlocks.stateTexturedBlock.getRegistryName()));
//        event.getRegistry().register(new ItemBlock(ModBlocks.blinkingBlock).setRegistryName(ModBlocks.blinkingBlock.getRegistryName()));
//        event.getRegistry().register(new ItemBlock(ModBlocks.firstBlock).setRegistryName(ModBlocks.firstBlock.getRegistryName()));
//        event.getRegistry().register(new ItemBlock(ModBlocks.simpleTexturedBlock).setRegistryName(ModBlocks.simpleTexturedBlock.getRegistryName()));
//        event.getRegistry().register(new ItemBlock(ModBlocks.multiTexturedBlock).setRegistryName(ModBlocks.multiTexturedBlock.getRegistryName()));
//        event.getRegistry().register(new ItemBlock(ModBlocks.bakedModelBlock).setRegistryName(ModBlocks.bakedModelBlock.getRegistryName()));
//        event.getRegistry().register(new ItemBlock(ModBlocks.testContainerBlock).setRegistryName(ModBlocks.testContainerBlock.getRegistryName()));
//        event.getRegistry().register(new ItemBlock(ModBlocks.dataBlock).setRegistryName(ModBlocks.dataBlock.getRegistryName()));
//        event.getRegistry().register(new ItemBlock(ModBlocks.modelBlock).setRegistryName(ModBlocks.modelBlock.getRegistryName()));
//        event.getRegistry().register(new ItemBlock(ModBlocks.pedestalBlock).setRegistryName(ModBlocks.pedestalBlock.getRegistryName()));
    }
}
