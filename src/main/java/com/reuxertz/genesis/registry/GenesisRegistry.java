package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.api.IGenesisRegistry;
import com.reuxertz.genesis.api.blocks.BaseBlock;
import com.reuxertz.genesis.api.blocks.BaseBlockGrowable;
import com.reuxertz.genesis.api.blocks.BaseBlockMetal;
import com.reuxertz.genesis.api.blocks.BaseBlockOre;
import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.items.BaseCropSeed;
import com.reuxertz.genesis.api.items.BaseItem;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.tileentity.TileEntityBaseCrop;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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
import java.util.Map;

import static com.reuxertz.genesis.Genesis.registry;

@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class GenesisRegistry implements IGenesisRegistry
{
    private static Map<String, RegistryObject> registryObjectHashMap = new HashMap<>();
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

//                if (regobj.tileEntityClass != null)
//                    GameRegistry.registerTileEntity(regobj.tileEntityClass, "genesis:tileEntityBaseCrop");
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

    public RegistryObject getRegistryObject(String name)
    {
        return registryObjectHashMap.get(name);
    }

    public IGenesisRegistry registerContent(RegistryObject registryObject)
    {
        if (registryObjectHashMap.keySet().contains(registryObject.name) ||
            registryObjectHashMap.values().contains(registryObject)  ||
            registryObjectList.contains(registryObject))
            return this;

        registryObjectHashMap.put(registryObject.name, registryObject);
        registryObjectList.add(registryObject);

        if (!registeredModIds.contains(registryObject.modId))
            registeredModIds.add(registryObject.modId);

        return this;
    }

    //Environment
    public IGenesisRegistry registerOre(String name)
    {
        registerContent(new RegistryObject(modId, "ore_" + name, new BaseBlockOre("ore_" + name)));
        return this;
    }
    public IGenesisRegistry registerMetalBlock(String name)
    {
        registerContent(new RegistryObject(modId, "block_" + name, new BaseBlockMetal("block_" + name)));
        return this;
    }
    public IGenesisRegistry registerIngot(String name)
    {
        registerContent(new RegistryObject(modId, "ingot_" + name, new BaseBlockMetal("ingot_" + name)));
        return this;
    }
    public IGenesisRegistry registerNugget(String name)
    {
        registerContent(new RegistryObject(modId, "nugget_" + name, new BaseBlockMetal("nugget_" + name)));
        return this;
    }
    public IGenesisRegistry registerArmor(String name, String type)
    {
        if (type != null)
            type = type + "_";

        if (type == null)
            type = "";

        registerContent(new RegistryObject(modId, "chestplate_" + type + name, new BaseBlockMetal("chestplate_" + name)));
        registerContent(new RegistryObject(modId, "boots_" + type + name, new BaseBlockMetal("boots_" + name)));
        registerContent(new RegistryObject(modId, "leggings_" + type + name, new BaseBlockMetal("leggings_" + name)));
        registerContent(new RegistryObject(modId, "helmet_" + type + name, new BaseBlockMetal("helmet_" + name)));

        return this;
    }
    public IGenesisRegistry registerArmorSet(String name)
    {
        registerArmor(name, null);
        registerArmor(name, "chain");
        registerArmor(name, "studded");

        return this;
    }
    public IGenesisRegistry registerMetal(String name)
    {
        this.registerMetal(name, true, true, true);
        return this;
    }
    public IGenesisRegistry registerMetal(String name, boolean isAlloy, boolean hasNuggetIngot, boolean enableArmorSet)
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

        return this;
    }

    //Plants
    public IGenesisRegistry registerCrop(String name)
    {
        BaseBlockGrowable blockCrop = new BaseBlockGrowable("crop_" + name);
        Item crop = new BaseItem(name);
        BaseCropSeed seed = new BaseCropSeed("seed_" + name, blockCrop);

        registerContent(new RegistryObject(modId, name, crop));
        registerContent(new RegistryObject(modId, "seed_" + name, seed));

        registerContent(new RegistryObject(modId, "crop_" + name, blockCrop));

        seed.setBlockCrop(blockCrop);
        blockCrop.setSeed(seed).setCrop(crop);

        return this;
    }

    //Plants
    public IGenesisRegistry registerEntity(String name)
    {
        registerContent(new RegistryObject(modId, name));
        return this;
    }

    //Ecosystem
    public IGenesisRegistry registerSpecies(String name, List<SpeciesFeature> speciesData, List<GeneData> genes)
    {
        SpeciesRegistry.registerSpecies(name, speciesData, genes);
        return this;
    }
}
