package com.reuxertz.genesis.util;

import com.reuxertz.genesis.api.blocks.BaseBlockMetal;
import com.reuxertz.genesis.api.blocks.BaseBlockOre;
import com.reuxertz.genesis.api.items.BaseIngot;
import com.reuxertz.genesis.api.items.BaseItem;
import com.reuxertz.genesis.api.items.BaseNugget;
import com.reuxertz.genesis.mod.Genesis;
import com.reuxertz.genesis.registry.RegistryObject;

import java.util.ArrayList;
import java.util.List;

import static com.reuxertz.genesis.mod.Genesis.registry;

public class RegistryHelper {

    public static RegistryObject registerOre(String name, String modId)
    {
        return Genesis.registry.registerContent(new RegistryObject(registry, modId, "ore_" + name, new BaseBlockOre("ore_" + name)));

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
            group.add(Genesis.registry.registerContent(new RegistryObject(registry, modId, "chain_" + name, new BaseItem("chain_" + name))));
            group.addAll(registerArmorSet(name, modId).getGroup());
        }

        result.groupWith(group);
        return result;
    }

    public static RegistryObject registerMetalBlock(String name, String modId)
    {
        return Genesis.registry.registerContent(new RegistryObject(registry, modId, "block_" + name, new BaseBlockMetal("block_" + name)));
    }

    public static RegistryObject registerIngot(String name, String modId)
    {
        return Genesis.registry.registerContent(new RegistryObject(registry, modId, "ingot_" + name, new BaseIngot("ingot_" + name)));
    }

    public static RegistryObject registerNugget(String name, String modId)
    {
        return Genesis.registry.registerContent(new RegistryObject(registry, modId, "nugget_" + name, new BaseNugget("nugget_" + name)));
    }

    public static RegistryObject registerArmor(String name, String modId, String type)
    {
        if (type != null)
            type = type + "_";

        if (type == null)
            type = "";

        RegistryObject result = Genesis.registry.registerContent(new RegistryObject(registry, modId, "chestplate_" + type + name, new BaseBlockMetal("chestplate_" + name)));
        List<RegistryObject> group = new ArrayList<>();

        group.add(Genesis.registry.registerContent(new RegistryObject(registry, modId, "boots_" + type + name, new BaseBlockMetal("boots_" + name))));
        group.add(Genesis.registry.registerContent(new RegistryObject(registry, modId, "leggings_" + type + name, new BaseBlockMetal("leggings_" + name))));
        group.add(Genesis.registry.registerContent(new RegistryObject(registry, modId, "helmet_" + type + name, new BaseBlockMetal("helmet_" + name))));

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

    public RegistryObject registerItem(String name, String modId)
    {
        return Genesis.registry.registerContent(new RegistryObject(registry, modId, name, new BaseItem(name)));

    }
}
