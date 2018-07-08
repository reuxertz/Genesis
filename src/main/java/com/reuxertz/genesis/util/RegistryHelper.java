package com.reuxertz.genesis.util;

import com.reuxertz.gaia.blocks.BlockBaseMetal;
import com.reuxertz.gaia.blocks.BlockBaseOre;
import com.reuxertz.gaia.items.IngotBase;
import com.reuxertz.genesis.api.items.ItemBase;
import com.reuxertz.gaia.items.NuggetBase;
import com.reuxertz.genesis.api.blocks.BaseBlockGrowable;
import com.reuxertz.genesis.api.items.CropSeedBase;
import com.reuxertz.genesis.mod.Genesis;
import com.reuxertz.genesis.registry.RegistryObject;

import java.util.ArrayList;
import java.util.List;

import static com.reuxertz.genesis.mod.Genesis.registry;

public class RegistryHelper {

    public static RegistryObject registerOre(String name, String modId)
    {
        return Genesis.registry.registerContent(new RegistryObject(registry, modId, "ore_" + name, new BlockBaseOre("ore_" + name)));

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
            group.add(Genesis.registry.registerContent(new RegistryObject(registry, modId, "chain_" + name, new ItemBase("chain_" + name))));
            group.addAll(registerArmorSet(name, modId).getGroup());
        }

        result.groupWith(group);
        return result;
    }

    public static RegistryObject registerMetalBlock(String name, String modId)
    {
        return Genesis.registry.registerContent(new RegistryObject(registry, modId, "block_" + name, new BlockBaseMetal("block_" + name)));
    }

    public static RegistryObject registerIngot(String name, String modId)
    {
        return Genesis.registry.registerContent(new RegistryObject(registry, modId, "ingot_" + name, new IngotBase("ingot_" + name)));
    }

    public static RegistryObject registerNugget(String name, String modId)
    {
        return Genesis.registry.registerContent(new RegistryObject(registry, modId, "nugget_" + name, new NuggetBase("nugget_" + name)));
    }

    public static RegistryObject registerArmor(String name, String modId, String type)
    {
        if (type != null)
            type = type + "_";

        if (type == null)
            type = "";

        RegistryObject result = Genesis.registry.registerContent(new RegistryObject(registry, modId, "chestplate_" + type + name, new BlockBaseMetal("chestplate_" + name)));
        List<RegistryObject> group = new ArrayList<>();

        group.add(Genesis.registry.registerContent(new RegistryObject(registry, modId, "boots_" + type + name, new BlockBaseMetal("boots_" + name))));
        group.add(Genesis.registry.registerContent(new RegistryObject(registry, modId, "leggings_" + type + name, new BlockBaseMetal("leggings_" + name))));
        group.add(Genesis.registry.registerContent(new RegistryObject(registry, modId, "helmet_" + type + name, new BlockBaseMetal("helmet_" + name))));

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
        BaseBlockGrowable blockCrop = new BaseBlockGrowable("crop_" + name);
        CropSeedBase crop = new CropSeedBase(name, blockCrop);
        CropSeedBase seed = new CropSeedBase("seed_" + name, blockCrop);

        RegistryObject result = Genesis.registry.registerContent(new RegistryObject(registry, modId, name, crop));
        List<RegistryObject> group = new ArrayList<>();

        group.add(Genesis.registry.registerContent(new RegistryObject(registry, modId, "seed_" + name, seed)));

        group.add(Genesis.registry.registerContent(new RegistryObject(registry, modId, "crop_" + name, blockCrop)));

        seed.setBlockCrop(blockCrop);
        crop.setBlockCrop(blockCrop);
        blockCrop.setSeed(seed).setCrop(crop);

        result.groupWith(group);
        return result;
    }
}
