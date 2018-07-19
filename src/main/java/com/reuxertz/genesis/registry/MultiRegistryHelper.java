package com.reuxertz.genesis.registry;

import com.reuxertz.gaia.blocks.BlockMetalBase;
import com.reuxertz.gaia.blocks.BlockOreBase;
import com.reuxertz.gaia.items.IngotBase;
import com.reuxertz.genesis.block.base.BlockCropBase;
import com.reuxertz.genesis.items.base.ItemBase;
import com.reuxertz.gaia.items.NuggetBase;
import com.reuxertz.genesis.items.base.CropSeedBase;
import com.reuxertz.genesisAPI.internal.GenesisAPI;
import com.reuxertz.genesisAPI.registry.RegistryObject;

import java.util.ArrayList;
import java.util.List;


public class MultiRegistryHelper {

    public static RegistryObject registerOre(String name, String modId)
    {
        return GenesisAPI.registry.registerContent(new RegistryObject(GenesisAPI.registry, modId, "ore_" + name, new BlockOreBase("ore_" + name)));

    }

    public static RegistryObject registerMetal(String name, String modId)
    {
        return registerMetal(name, modId, true, true, true);
    }

    public static RegistryObject registerMetal(String name, String modId, boolean isAlloy, boolean hasNuggetIngot, boolean enableArmorSet)
    {
        RegistryObject result;
        List<RegistryObject> group = new ArrayList<>();
        if (!isAlloy) {
            group.add(registerOre(name, modId));
        }

        result = registerMetalBlock(name, modId);

        if (hasNuggetIngot)
        {
            group.add(registerIngot(name, modId));
            group.add(registerNugget(name, modId));
        }

        if (enableArmorSet) {
            group.add(GenesisAPI.registry.registerContent(new RegistryObject(GenesisAPI.registry, modId, "chain_" + name, new ItemBase("chain_" + name))));
            group.addAll(registerArmorSet(name, modId).getGroup());
        }

        result.groupWith(group);
        return result;
    }

    public static RegistryObject registerMetalBlock(String name, String modId)
    {
        return GenesisAPI.registry.registerContent(new RegistryObject(GenesisAPI.registry, modId, "block_" + name, new BlockMetalBase("block_" + name)));
    }

    public static RegistryObject registerIngot(String name, String modId)
    {
        return GenesisAPI.registry.registerContent(new RegistryObject(GenesisAPI.registry, modId, "ingot_" + name, new IngotBase("ingot_" + name)));
    }

    public static RegistryObject registerNugget(String name, String modId)
    {
        return GenesisAPI.registry.registerContent(new RegistryObject(GenesisAPI.registry, modId, "nugget_" + name, new NuggetBase("nugget_" + name)));
    }

    public static RegistryObject registerArmor(String name, String modId, String type)
    {
        if (type != null)
            type = type + "_";

        if (type == null)
            type = "";

        RegistryObject result = GenesisAPI.registry.registerContent(new RegistryObject(GenesisAPI.registry, modId, "chestplate_" + type + name, new BlockMetalBase("chestplate_" + name)));
        List<RegistryObject> group = new ArrayList<>();

        group.add(GenesisAPI.registry.registerContent(new RegistryObject(GenesisAPI.registry, modId, "boots_" + type + name, new BlockMetalBase("boots_" + name))));
        group.add(GenesisAPI.registry.registerContent(new RegistryObject(GenesisAPI.registry, modId, "leggings_" + type + name, new BlockMetalBase("leggings_" + name))));
        group.add(GenesisAPI.registry.registerContent(new RegistryObject(GenesisAPI.registry, modId, "helmet_" + type + name, new BlockMetalBase("helmet_" + name))));

        result.groupWith(group);
        return result;
    }

    public static RegistryObject registerArmorSet(String name, String modId)
    {
        RegistryObject result = registerArmor(name, modId, null);
        List<RegistryObject> group = new ArrayList<>();

        group.addAll(registerArmor(name, modId, "chain").getGroup());
        group.addAll(registerArmor(name, modId, "studded").getGroup());

        result.groupWith(group);
        return result;
    }

    //Plants
    public static RegistryObject registerCrop(String name, String modId)
    {
        BlockCropBase blockCrop = new BlockCropBase("crop_" + name);
        CropSeedBase crop = new CropSeedBase(name, blockCrop);
        CropSeedBase seed = new CropSeedBase("seed_" + name, blockCrop);

        RegistryObject result = GenesisAPI.registry.registerContent(new RegistryObject(GenesisAPI.registry, modId, name, crop));
        List<RegistryObject> group = new ArrayList<>();

        group.add(GenesisAPI.registry.registerContent(new RegistryObject(GenesisAPI.registry, modId, "seed_" + name, seed)));

        group.add(GenesisAPI.registry.registerContent(new RegistryObject(GenesisAPI.registry, modId, "crop_" + name, blockCrop)));

        seed.setBlockCrop(blockCrop);
        crop.setBlockCrop(blockCrop);
        blockCrop.setSeed(seed).setCrop(crop);

        result.groupWith(group);
        return result;
    }
}
