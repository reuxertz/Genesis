package com.reuxertz.gaia.items;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ArmorMaterial {

//    LEATHER("leather",    5, new int[]{       1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F),
//    CHAIN("chainmail",    15, new int[]{      1, 4, 5, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F),
//    IRON("iron",          15, new int[]{      2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F),
//    GOLD("gold",          7, new int[]{       1, 3, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F),
//    DIAMOND("diamond",    33, new int[]{      3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
//
//    public static final ItemArmor.ArmorMaterial STUDDED_COPPER_MATERIAL = EnumHelper.addArmorMaterial(
//            "studded_copper", "studded_copper", 5, new int[]{ 1, 2, 3, 1},  15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
//    public static final ItemArmor.ArmorMaterial STUDDED_BRONZE_MATERIAL = EnumHelper.addToolMaterial("studded_bronze",    2, 1000, 6.5F, 2.5F, 12);
//    public static final ItemArmor.ArmorMaterial STUDDED_STEEL_MATERIAL = EnumHelper.addToolMaterial("studded_steel",      3, 1500, 7.5F, 2.75F, 15);
//
//    public static final ItemArmor.ArmorMaterial CHAIN_COPPER_MATERIAL = EnumHelper.addToolMaterial("chain_copper",        2, 200, 5.0F, 1.5F, 8);
//    public static final ItemArmor.ArmorMaterial CHAIN_BRONZE_MATERIAL = EnumHelper.addToolMaterial("chain_bronze",        2, 1000, 6.5F, 2.5F, 12);
//    public static final ItemArmor.ArmorMaterial CHAIN_STEEL_MATERIAL = EnumHelper.addToolMaterial("chain_steel",          3, 1500, 7.5F, 2.75F, 15);
//
    public static final ItemArmor.ArmorMaterial COPPER_MATERIAL = EnumHelper.addArmorMaterial(
        "copper", "copper", 10, new int[]{ 1, 3, 4, 1},  15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.5F);
    public static final ItemArmor.ArmorMaterial BRONZE_MATERIAL = EnumHelper.addArmorMaterial(
            "copper", "copper", 10, new int[]{ 1, 3, 4, 1},  15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.5F);
    public static final ItemArmor.ArmorMaterial STEEL_MATERIAL = EnumHelper.addArmorMaterial(
            "copper", "copper", 10, new int[]{ 1, 3, 4, 1},  15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.5F);

    //private ArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn)
}
