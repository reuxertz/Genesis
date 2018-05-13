package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.api.IGenesisRegistry;
import com.reuxertz.genesis.api.block.BaseBlock;
import com.reuxertz.genesis.api.block.BaseBlockMetal;
import com.reuxertz.genesis.api.block.BaseBlockOre;
import com.reuxertz.genesis.api.block.BaseBlockCrop;
import com.reuxertz.genesis.api.items.BaseCropSeed;
import com.reuxertz.genesis.api.items.BaseItem;
import com.reuxertz.genesis.tileentity.BaseTileEntity;
import com.reuxertz.genesis.tileentity.TileEntityBaseCrop;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.reuxertz.genesis.Genesis.registry;

@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class GenesisRegistry implements IGenesisRegistry
{
    private static HashMap<String, RegistryObject> registryObjectHashMap = new HashMap<>();
    private static List<RegistryObject> registryObjectList = new ArrayList<>();
    private static List<String> registeredModIds = new ArrayList<>();

    private final String modId;

    public GenesisRegistry(String modId) {
        this.modId = modId;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++) {

            RegistryObject regobj = GenesisRegistry.registryObjectList.get(i);

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
                Item item = GenesisRegistry.registryObjectList.get(i).item;
                event.getRegistry().register(item);
                regobj.registerItem();
            }
        }

        return;
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        GameRegistry.registerTileEntity(TileEntityBaseCrop.class, "genesis:tileEntityBaseCrop");

        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++)
            if (GenesisRegistry.registryObjectList.get(i).block != null) {
                if (GenesisRegistry.registryObjectList.get(i).isBlockRegistered())
                    continue;

                RegistryObject regobj = registry.registryObjectList.get(i);
                event.getRegistry().register((Block)regobj.block);
                regobj.registerBlock();

                if (regobj.tileEntityClass != null)
                    GameRegistry.registerTileEntity(regobj.tileEntityClass, "genesis:tileEntityBaseCrop");
            }
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event)
    {
        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++)
            if (GenesisRegistry.registryObjectList.get(i).entityEntry != null) {
//                if (registryObjectList.get(i).isEntityRegistered())
//                    continue;

                RegistryObject regobj = registry.registryObjectList.get(i);
                event.getRegistry().register(regobj.entityEntry);
                regobj.registerEntity();

            }
    }

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++)
            GenesisRegistry.registryObjectList.get(i).initModel();

        return;
    }

    public void registerContent(RegistryObject registryObject)
    {
        if (registryObjectHashMap.keySet().contains(registryObject.name) ||
            registryObjectHashMap.values().contains(registryObject)  ||
            registryObjectList.contains(registryObject))
            return;

        registryObjectHashMap.put(registryObject.name, registryObject);
        registryObjectList.add(registryObject);

        if (!registeredModIds.contains(registryObject.modId))
            registeredModIds.add(registryObject.modId);
    }

    //Environment
    public void registerOre(String name)
    {
        registerContent(new RegistryObject(modId, "ore_" + name, new BaseBlockOre("ore_" + name)));
    }
    public void registerMetalBlock(String name)
    {
        registerContent(new RegistryObject(modId, "block_" + name, new BaseBlockMetal("block_" + name)));
    }
    public void registerIngot(String name)
    {
        registerContent(new RegistryObject(modId, "ingot_" + name, new BaseBlockMetal("ingot_" + name)));
    }
    public void registerNugget(String name)
    {
        registerContent(new RegistryObject(modId, "nugget_" + name, new BaseBlockMetal("nugget_" + name)));
    }
    public void registerArmor(String name, String type)
    {
        if (type != null)
            type = type + "_";

        if (type == null)
            type = "";

        registerContent(new RegistryObject(modId, "chestplate_" + type + name, new BaseBlockMetal("chestplate_" + name)));
        registerContent(new RegistryObject(modId, "boots_" + type + name, new BaseBlockMetal("boots_" + name)));
        registerContent(new RegistryObject(modId, "leggings_" + type + name, new BaseBlockMetal("leggings_" + name)));
        registerContent(new RegistryObject(modId, "helmet_" + type + name, new BaseBlockMetal("helmet_" + name)));
    }
    public void registerArmorSet(String name)
    {
        registerArmor(name, null);
        registerArmor(name, "chain");
        registerArmor(name, "studded");
    }
    public void registerMetal(String name)
    {
        this.registerMetal(name, true, true, true);
    }
    public void registerMetal(String name, boolean isAlloy, boolean hasNuggetIngot, boolean enableArmorSet)
    {
        if (!isAlloy) {
            registerOre(name);
        }

        registerMetalBlock(name);

        if (hasNuggetIngot)
        {
            registerIngot(name);
            registerNugget(name);
        }

        if (enableArmorSet) {
            registerContent(new RegistryObject(modId, "chain_" + name, new BaseItem("chain_" + name)));
            registerArmorSet(name);
        }

    }

    //Ecosystem
    public void registerCrop(String name)
    {
        BaseBlockCrop blockCrop = new BaseBlockCrop("crop_" + name);
        Item crop = new BaseItem(name);
        Item seed = new BaseCropSeed("seed_" + name, blockCrop);

        registerContent(new RegistryObject(modId, name, crop));
        registerContent(new RegistryObject(modId, "seed_" + name, seed));

        registerContent(new RegistryObject(modId, "crop_" + name, blockCrop));

        blockCrop.setSeed(seed).setCrop(crop);


    }
}
