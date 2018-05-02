package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.block.SimpleBlock;
import com.reuxertz.genesis.item.SimpleItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class BlockRegistry {

    @GameRegistry.ObjectHolder("genesis:simpleblock")
    public static SimpleBlock simpleBlock;
    @GameRegistry.ObjectHolder("genesis:simpleblock2")
    public static SimpleBlock simpleBlock2;

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        GenesisRegistry.initModels();
        simpleBlock.initModel();
        //simpleBlock2.initModel();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        GenesisRegistry.registerItems(event);

        ItemBlock bl = new ItemBlock(simpleBlock);
        bl.setRegistryName(simpleBlock.getUnlocalizedName());
        bl.setUnlocalizedName(simpleBlock.getUnlocalizedName());
        event.getRegistry().register(bl);

//        ItemBlock b2 = new ItemBlock(simpleBlock2);
//        b2.setRegistryName(simpleBlock2.getUnlocalizedName());
//        b2.setUnlocalizedName(simpleBlock2.getUnlocalizedName());
//        event.getRegistry().register(b2);
}

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        GenesisRegistry.registerBlocks(event);

        event.getRegistry().register(new SimpleBlock("simpleblock"));
        //event.getRegistry().register(new SimpleBlock("simpleblock2"));
//        event.getRegistry().register(new BlinkingBlock());
//        event.getRegistry().register(new FirstBlock());
//        event.getRegistry().register(new SimpleTexturedBlock());
//        event.getRegistry().register(new MultiTexturedBlock());
//        event.getRegistry().register(new BakedModelBlock());
//        event.getRegistry().register(new TestContainerBlock());
//        event.getRegistry().register(new DataBlock());
//        event.getRegistry().register(new ModelBlock());
//        event.getRegistry().register(new PedestalBlock());
//
//        GameRegistry.registerTileEntity(BlinkingTileEntity.class, ModTut.MODID + "_blinkingblock");
//        GameRegistry.registerTileEntity(TestContainerTileEntity.class, ModTut.MODID + "_testcontainerblock");
//        GameRegistry.registerTileEntity(DataTileEntity.class, ModTut.MODID + "_datablock");
//        GameRegistry.registerTileEntity(PedestalTileEntity.class, ModTut.MODID + "_pedestalblock");
    }
}
