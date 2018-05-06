package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.api.block.BaseBlock;
import com.reuxertz.genesis.block.SimpleBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class GenesisRegistry
{
    public static HashMap<String, RegistryObject> registryObjectHashMap = new HashMap<>();
    public static List<RegistryObject> registryObjectList = new ArrayList<>();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        for (int i = 0; i < registryObjectList.size(); i++) {

            RegistryObject regobj = registryObjectList.get(i);

            //I feel like this should be here, but it doesn't work with it
//            if (regobj.isItemRegistered())
//                continue;

            if (regobj.block != null && regobj.item == null &&
                    regobj.block instanceof BaseBlock && ((BaseBlock)regobj.block).isSimple)
            {
                Block block =(Block)regobj.block;

                ItemBlock bl = new ItemBlock(block);
                bl.setRegistryName(block.getUnlocalizedName());
                bl.setUnlocalizedName(block.getUnlocalizedName());
                regobj.item = bl;
            }

            if (regobj.item != null) {
                Item item = registryObjectList.get(i).item;
                event.getRegistry().register(item);
                regobj.registerItem();
            }
        }

        return;
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        for (int i = 0; i < registryObjectList.size(); i++)
            if (registryObjectList.get(i).block != null) {
                if (registryObjectList.get(i).isBlockRegistered())
                    continue;

                RegistryObject regobj = registryObjectList.get(i);
                event.getRegistry().register((Block)regobj.block);
                regobj.registerBlock();

            }

        event.getRegistry().register(new SimpleBlock("simpleblock"));
    }

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        for (int i = 0; i < registryObjectList.size(); i++)
            registryObjectList.get(i).initModel();

        return;
    }

    public static void registerContent(RegistryObject registryObject)
    {
        if (registryObjectHashMap.keySet().contains(registryObject.name) ||
            registryObjectHashMap.values().contains(registryObject)  ||
            registryObjectList.contains(registryObject))
            return;

        registryObjectHashMap.put(registryObject.name, registryObject);
        registryObjectList.add(registryObject);
    }
}
