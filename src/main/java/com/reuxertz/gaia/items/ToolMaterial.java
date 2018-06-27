package com.reuxertz.gaia.items;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class ToolMaterial {

//    WOOD(0, 59, 2.0F, 0.0F, 15),
//    STONE(1, 131, 4.0F, 1.0F, 5),
//    IRON(2, 250, 6.0F, 2.0F, 14),
//    DIAMOND(3, 1561, 8.0F, 3.0F, 10),
//    GOLD(0, 32, 12.0F, 0.0F, 22);

    public static final Item.ToolMaterial COPPER_MATERIAL = EnumHelper.addToolMaterial("copper", 2, 200, 5.0F, 1.5F, 8);
    public static final Item.ToolMaterial BRONZE_MATERIAL = EnumHelper.addToolMaterial("bronze", 2, 1000, 6.5F, 2.5F, 12);
    public static final Item.ToolMaterial STEEL_MATERIAL = EnumHelper.addToolMaterial("copper", 3, 1500, 7.5F, 2.75F, 15);
}
