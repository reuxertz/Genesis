package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.api.block.BaseBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.reuxertz.genesis.registry.BlockRegistry.simpleBlock2;

public class GenesisRegistry
{
    public static HashMap<String, RegistryObject> registryObjectHashMap = new HashMap<>();
    public static List<RegistryObject> registryObjectList = new ArrayList<>();

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

    public static void registerItems(RegistryEvent.Register<Item> event)
    {
//        String n = simpleBlock2.getUnlocalizedName();
//        ItemBlock b2 = new ItemBlock(simpleBlock2);
//        b2.setRegistryName(simpleBlock2.getUnlocalizedName());
//        b2.setUnlocalizedName(simpleBlock2.getUnlocalizedName());
//        event.getRegistry().register(b2);

        for (int i = 0; i < registryObjectList.size(); i++) {
            RegistryObject regobj = registryObjectList.get(i);
            if (regobj.block != null && regobj.item == null &&
                regobj.block instanceof BaseBlock && ((BaseBlock)regobj.block).isSimple)
            {
                ItemBlock bl = new ItemBlock(regobj.block);
                bl.setRegistryName(regobj.block.getUnlocalizedName());
                bl.setUnlocalizedName(regobj.block.getUnlocalizedName());
                regobj.item = bl;
            }

            if (regobj.item != null) {
                if (regobj.isRegistered())
                    continue;
                Item item = registryObjectList.get(i).item;
                event.getRegistry().register(item);
                regobj.Register();
            }
        }

        return;
    }

    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        for (int i = 0; i < registryObjectList.size(); i++)
            if (registryObjectList.get(i).block != null) {
                if (registryObjectList.get(i).isRegistered())
                    continue;
                event.getRegistry().register(registryObjectList.get(i).block);
            }

        return;
    }

}
