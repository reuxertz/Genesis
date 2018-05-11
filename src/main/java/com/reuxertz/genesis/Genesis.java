package com.reuxertz.genesis;

import com.reuxertz.genesis.api.IGenesisPlugin;
import com.reuxertz.genesis.api.block.BaseBlockOre;
import com.reuxertz.genesis.api.block.BaseCrop;
import com.reuxertz.genesis.internal.GenesisApiHandler;
import com.reuxertz.genesis.api.items.BaseArmor;
import com.reuxertz.genesis.api.items.material.ArmorMaterial;
import com.reuxertz.genesis.api.items.material.ToolMaterial;
import com.reuxertz.genesis.api.items.tools.*;
import com.reuxertz.genesis.proxy.CommonProxy;
import com.reuxertz.genesis.registry.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod(modid = Genesis.MODID, name = Genesis.NAME, version = Genesis.VERSION, dependencies = "required-after:forge@[14.23.3.2655,)", useMetadata = true)
public class Genesis
{
    public static final String MODID = "genesis";
    public static final String NAME = "Genesis";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "com.reuxertz.genesis.proxy.ClientProxy", serverSide = "com.reuxertz.genesis.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static Genesis instance;
    public static List<IGenesisPlugin> plugins = new ArrayList();
    public static GenesisRegistry registry = new GenesisRegistry("genesis");
    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){

        GenesisApiHandler.loadPlugins(event.getAsmData());

        //registry.registerContent();
        
        //GenesisRegistry.instance.registerContent(new RegistryObject("simpleitem", new SimpleItem("simpleitem2")));
//
//        GenesisRegistry.instance.registerContent(new RegistryObject("simpleblock2", new BaseBlockOre("simpleblock2")));
//        GenesisRegistry.instance.registerContent(new RegistryObject("simpleblock3", new BaseBlockOre("simpleblock3")));

//        Genesis.registry.registerContent(new RegistryObject("ore_copper", new BaseBlockOre("ore_copper")));
//        Genesis.registry.registerContent(new RegistryObject("ore_tin", new BaseBlockOre("ore_tin")));
//        Genesis.registry.registerContent(new RegistryObject("ore_sulfur", new BaseBlockOre("ore_sulfur")));
//        Genesis.registry.registerContent(new RegistryObject("ore_salt", new BaseBlockOre("ore_salt")));
//        Genesis.registry.registerContent(new RegistryObject("ore_lead", new BaseBlockOre("ore_lead")));
//        Genesis.registry.registerContent(new RegistryObject("ore_silver", new BaseBlockOre("ore_silver")));
//        Genesis.registry.registerContent(new RegistryObject("ore_aluminum", new BaseBlockOre("ore_aluminum")));
//        Genesis.registry.registerContent(new RegistryObject("ore_nickel", new BaseBlockOre("ore_nickel")));
//        Genesis.registry.registerContent(new RegistryObject("ore_zinc", new BaseBlockOre("ore_zinc")));
//        Genesis.registry.registerContent(new RegistryObject("ore_sapphire", new BaseBlockOre("ore_sapphire")));
//        Genesis.registry.registerContent(new RegistryObject("ore_ruby", new BaseBlockOre("ore_ruby")));

//        Genesis.registry.registerContent(new RegistryObject("block_copper", new BaseBlockOre("block_copper")));
//        Genesis.registry.registerContent(new RegistryObject("block_tin", new BaseBlockOre("block_tin")));
//        Genesis.registry.registerContent(new RegistryObject("block_sulfur", new BaseBlockOre("block_sulfur")));
//        Genesis.registry.registerContent(new RegistryObject("block_salt", new BaseBlockOre("block_salt")));
//        Genesis.registry.registerContent(new RegistryObject("block_lead", new BaseBlockOre("block_lead")));
//        Genesis.registry.registerContent(new RegistryObject("block_silver", new BaseBlockOre("block_silver")));
//        Genesis.registry.registerContent(new RegistryObject("block_aluminum", new BaseBlockOre("block_aluminum")));
//        Genesis.registry.registerContent(new RegistryObject("block_nickel", new BaseBlockOre("block_nickel")));
//        Genesis.registry.registerContent(new RegistryObject("block_zinc", new BaseBlockOre("block_zinc")));
//        Genesis.registry.registerContent(new RegistryObject("block_sapphire", new BaseBlockOre("block_sapphire")));
//        Genesis.registry.registerContent(new RegistryObject("block_ruby", new BaseBlockOre("block_ruby")));
//
//        Genesis.registry.registerContent(new RegistryObject("ingot_copper", new BaseBlockOre("ingot_copper")));
//        Genesis.registry.registerContent(new RegistryObject("ingot_tin", new BaseBlockOre("ingot_tin")));
//        Genesis.registry.registerContent(new RegistryObject("ingot_lead", new BaseBlockOre("ingot_lead")));
//        Genesis.registry.registerContent(new RegistryObject("ingot_silver", new BaseBlockOre("ingot_silver")));
//        Genesis.registry.registerContent(new RegistryObject("ingot_aluminum", new BaseBlockOre("ingot_aluminum")));
//        Genesis.registry.registerContent(new RegistryObject("ingot_nickel", new BaseBlockOre("ingot_nickel")));
//        Genesis.registry.registerContent(new RegistryObject("ingot_zinc", new BaseBlockOre("ingot_zinc")));
//
//        Genesis.registry.registerContent(new RegistryObject("nugget_copper", new BaseBlockOre("nugget_copper")));
//        Genesis.registry.registerContent(new RegistryObject("nugget_tin", new BaseBlockOre("nugget_tin")));
//        Genesis.registry.registerContent(new RegistryObject("nugget_lead", new BaseBlockOre("nugget_lead")));
//        Genesis.registry.registerContent(new RegistryObject("nugget_silver", new BaseBlockOre("nugget_silver")));
//        Genesis.registry.registerContent(new RegistryObject("nugget_aluminum", new BaseBlockOre("nugget_aluminum")));
//        Genesis.registry.registerContent(new RegistryObject("nugget_nickel", new BaseBlockOre("nugget_nickel")));
//        Genesis.registry.registerContent(new RegistryObject("nugget_zinc", new BaseBlockOre("nugget_zinc")));
//        Genesis.registry.registerContent(new RegistryObject("nugget_sapphire", new BaseBlockOre("nugget_sapphire")));
//        Genesis.registry.registerContent(new RegistryObject("nugget_ruby", new BaseBlockOre("nugget_ruby")));
//
//        Genesis.registry.registerContent(new RegistryObject("chain_copper", new BaseBlockOre("chain_copper")));
//        Genesis.registry.registerContent(new RegistryObject("chain_iron", new BaseBlockOre("chain_iron")));
//        Genesis.registry.registerContent(new RegistryObject("chain_bronze", new BaseBlockOre("chain_bronze")));
//        Genesis.registry.registerContent(new RegistryObject("chain_steel", new BaseBlockOre("chain_steel")));
//
//        Genesis.registry.registerContent(new RegistryObject("sword_copper", new BaseSword("sword_copper", ToolMaterial.COPPER_MATERIAL, CreativeTabs.MISC)));
//        Genesis.registry.registerContent(new RegistryObject("axe_copper", new BaseAxe("axe_copper", ToolMaterial.COPPER_MATERIAL, 1, 1, CreativeTabs.MISC)));
//        Genesis.registry.registerContent(new RegistryObject("hoe_copper", new BaseHoe("hoe_copper", ToolMaterial.COPPER_MATERIAL, CreativeTabs.MISC)));
//        Genesis.registry.registerContent(new RegistryObject("shovel_copper", new BaseShovel("shovel_copper", ToolMaterial.COPPER_MATERIAL, CreativeTabs.MISC)));
//        Genesis.registry.registerContent(new RegistryObject("pickaxe_copper", new BasePickaxe("pickaxe_copper", ToolMaterial.COPPER_MATERIAL, CreativeTabs.MISC)));
//        Genesis.registry.registerContent(new RegistryObject("hammer_copper", new BaseHammer("hammer_copper", ToolMaterial.COPPER_MATERIAL, 1, 1, CreativeTabs.MISC)));
//
//        Genesis.registry.registerContent(new RegistryObject("boots_copper", new BaseArmor("boots_copper", ArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.FEET)));
//        Genesis.registry.registerContent(new RegistryObject("helmet_copper", new BaseArmor("helmet_copper", ArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.HEAD)));
//        Genesis.registry.registerContent(new RegistryObject("leggings_copper", new BaseArmor("leggings_copper", ArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.LEGS)));
//        Genesis.registry.registerContent(new RegistryObject("chestplate_copper", new BaseArmor("chestplate_copper", ArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.CHEST)));
//        Genesis.registry.registerContent(new RegistryObject("boots_chain_copper", new BaseArmor("boots_chain_copper", ArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.FEET)));
//        Genesis.registry.registerContent(new RegistryObject("helmet_chain_copper", new BaseArmor("helmet_chain_copper", ArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.HEAD)));
//        Genesis.registry.registerContent(new RegistryObject("leggings_chain_copper", new BaseArmor("leggings_chain_copper", ArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.LEGS)));
//        Genesis.registry.registerContent(new RegistryObject("chestplate_chain_copper", new BaseArmor("chestplate_chain_copper", ArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.CHEST)));
//        Genesis.registry.registerContent(new RegistryObject("boots_studded_copper", new BaseArmor("boots_studded_copper", ArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.FEET)));
//        Genesis.registry.registerContent(new RegistryObject("helmet_studded_copper", new BaseArmor("helmet_studded_copper", ArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.HEAD)));
//        Genesis.registry.registerContent(new RegistryObject("leggings_studded_copper", new BaseArmor("leggings_studded_copper", ArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.LEGS)));
//        Genesis.registry.registerContent(new RegistryObject("chestplate_studded_copper", new BaseArmor("chestplate_studded_copper", ArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.CHEST)));
//
//        Genesis.registry.registerContent(new RegistryObject("sword_bronze", new BaseSword("sword_bronze", ToolMaterial.COPPER_MATERIAL, CreativeTabs.MISC)));
//        Genesis.registry.registerContent(new RegistryObject("axe_bronze", new BaseAxe("axe_bronze", ToolMaterial.BRONZE_MATERIAL, 1, 1, CreativeTabs.MISC)));
//        Genesis.registry.registerContent(new RegistryObject("hoe_bronze", new BaseHoe("hoe_bronze", ToolMaterial.BRONZE_MATERIAL, CreativeTabs.MISC)));
//        Genesis.registry.registerContent(new RegistryObject("shovel_bronze", new BaseShovel("shovel_bronze", ToolMaterial.BRONZE_MATERIAL, CreativeTabs.MISC)));
//        Genesis.registry.registerContent(new RegistryObject("pickaxe_bronze", new BasePickaxe("pickaxe_bronze", ToolMaterial.BRONZE_MATERIAL, CreativeTabs.MISC)));
//        Genesis.registry.registerContent(new RegistryObject("hammer_bronze", new BaseHammer("hammer_bronze", ToolMaterial.BRONZE_MATERIAL, 1, 1, CreativeTabs.MISC)));
//
//        Genesis.registry.registerContent(new RegistryObject("boots_bronze", new BaseArmor("boots_bronze", ArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.FEET)));
//        Genesis.registry.registerContent(new RegistryObject("helmet_bronze", new BaseArmor("helmet_bronze", ArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.HEAD)));
//        Genesis.registry.registerContent(new RegistryObject("leggings_bronze", new BaseArmor("leggings_bronze", ArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.LEGS)));
//        Genesis.registry.registerContent(new RegistryObject("chestplate_bronze", new BaseArmor("chestplate_bronze", ArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.CHEST)));
//        Genesis.registry.registerContent(new RegistryObject("boots_chain_bronze", new BaseArmor("boots_chain_bronze", ArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.FEET)));
//        Genesis.registry.registerContent(new RegistryObject("helmet_chain_bronze", new BaseArmor("helmet_chain_bronze", ArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.HEAD)));
//        Genesis.registry.registerContent(new RegistryObject("leggings_chain_bronze", new BaseArmor("leggings_chain_bronze", ArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.LEGS)));
//        Genesis.registry.registerContent(new RegistryObject("chestplate_chain_bronze", new BaseArmor("chestplate_chain_bronze", ArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.CHEST)));
//        Genesis.registry.registerContent(new RegistryObject("boots_studded_bronze", new BaseArmor("boots_studded_bronze", ArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.FEET)));
//        Genesis.registry.registerContent(new RegistryObject("helmet_studded_bronze", new BaseArmor("helmet_studded_bronze", ArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.HEAD)));
//        Genesis.registry.registerContent(new RegistryObject("leggings_studded_bronze", new BaseArmor("leggings_studded_bronze", ArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.LEGS)));
//        Genesis.registry.registerContent(new RegistryObject("chestplate_studded_bronze", new BaseArmor("chestplate_studded_bronze", ArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.CHEST)));
//
//        Genesis.registry.registerContent(new RegistryObject("axe_iron", new BaseSword("axe_iron", Item.ToolMaterial.IRON, CreativeTabs.MISC)));
//        Genesis.registry.registerContent(new RegistryObject("hoe_iron", new BaseHoe("hoe_iron", Item.ToolMaterial.IRON, CreativeTabs.MISC)));
//        Genesis.registry.registerContent(new RegistryObject("shovel_iron", new BaseShovel("shovel_iron", Item.ToolMaterial.IRON, CreativeTabs.MISC)));
//        Genesis.registry.registerContent(new RegistryObject("pickaxe_iron", new BasePickaxe("pickaxe_iron", Item.ToolMaterial.IRON, CreativeTabs.MISC)));
//        Genesis.registry.registerContent(new RegistryObject("hammer_iron", new BaseHammer("hammer_iron", Item.ToolMaterial.IRON, 1, 1, CreativeTabs.MISC)));
//
//        Genesis.registry.registerContent(new RegistryObject("boots_iron", new BaseArmor("boots_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.FEET)));
//        Genesis.registry.registerContent(new RegistryObject("helmet_iron", new BaseArmor("helmet_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.HEAD)));
//        Genesis.registry.registerContent(new RegistryObject("leggings_iron", new BaseArmor("leggings_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.LEGS)));
//        Genesis.registry.registerContent(new RegistryObject("chestplate_iron", new BaseArmor("chestplate_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.CHEST)));
//        Genesis.registry.registerContent(new RegistryObject("boots_chain_iron", new BaseArmor("boots_chain_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.FEET)));
//        Genesis.registry.registerContent(new RegistryObject("helmet_chain_iron", new BaseArmor("helmet_chain_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.HEAD)));
//        Genesis.registry.registerContent(new RegistryObject("leggings_chain_iron", new BaseArmor("leggings_chain_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.LEGS)));
//        Genesis.registry.registerContent(new RegistryObject("chestplate_chain_iron", new BaseArmor("chestplate_chain_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.CHEST)));
//        Genesis.registry.registerContent(new RegistryObject("boots_studded_iron", new BaseArmor("boots_studded_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.FEET)));
//        Genesis.registry.registerContent(new RegistryObject("helmet_studded_iron", new BaseArmor("helmet_studded_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.HEAD)));
//        Genesis.registry.registerContent(new RegistryObject("leggings_studded_iron", new BaseArmor("leggings_studded_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.LEGS)));
//        Genesis.registry.registerContent(new RegistryObject("chestplate_studded_iron", new BaseArmor("chestplate_studded_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.CHEST)));
//
//        Genesis.registry.registerContent(new RegistryObject("sword_steel", new BaseSword("sword_steel", ToolMaterial.COPPER_MATERIAL, CreativeTabs.MISC)));
//        Genesis.registry.registerContent(new RegistryObject("axe_steel", new BaseAxe("axe_steel", ToolMaterial.STEEL_MATERIAL, 1, 1, CreativeTabs.MISC)));
//        Genesis.registry.registerContent(new RegistryObject("hoe_steel", new BaseHoe("hoe_steel", ToolMaterial.STEEL_MATERIAL, CreativeTabs.MISC)));
//        Genesis.registry.registerContent(new RegistryObject("shovel_steel", new BaseShovel("shovel_steel", ToolMaterial.STEEL_MATERIAL, CreativeTabs.MISC)));
//        Genesis.registry.registerContent(new RegistryObject("pickaxe_steel", new BasePickaxe("pickaxe_steel", ToolMaterial.STEEL_MATERIAL, CreativeTabs.MISC)));
//        Genesis.registry.registerContent(new RegistryObject("hammer_steel", new BaseHammer("hammer_steel", ToolMaterial.STEEL_MATERIAL, 1, 1, CreativeTabs.MISC)));
//
//        Genesis.registry.registerContent(new RegistryObject("boots_steel", new BaseArmor("boots_steel", ArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.FEET)));
//        Genesis.registry.registerContent(new RegistryObject("helmet_steel", new BaseArmor("helmet_steel", ArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.HEAD)));
//        Genesis.registry.registerContent(new RegistryObject("leggings_steel", new BaseArmor("leggings_steel", ArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.LEGS)));
//        Genesis.registry.registerContent(new RegistryObject("chestplate_steel", new BaseArmor("chestplate_steel", ArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.CHEST)));
//        Genesis.registry.registerContent(new RegistryObject("boots_chain_steel", new BaseArmor("boots_chain_steel", ArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.FEET)));
//        Genesis.registry.registerContent(new RegistryObject("helmet_chain_steel", new BaseArmor("helmet_chain_steel", ArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.HEAD)));
//        Genesis.registry.registerContent(new RegistryObject("leggings_chain_steel", new BaseArmor("leggings_chain_steel", ArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.LEGS)));
//        Genesis.registry.registerContent(new RegistryObject("chestplate_chain_steel", new BaseArmor("chestplate_chain_steel", ArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.CHEST)));
//        Genesis.registry.registerContent(new RegistryObject("boots_studded_steel", new BaseArmor("boots_studded_steel", ArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.FEET)));
//        Genesis.registry.registerContent(new RegistryObject("helmet_studded_steel", new BaseArmor("helmet_studded_steel", ArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.HEAD)));
//        Genesis.registry.registerContent(new RegistryObject("leggings_studded_steel", new BaseArmor("leggings_studded_steel", ArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.LEGS)));
//        Genesis.registry.registerContent(new RegistryObject("chestplate_studded_steel", new BaseArmor("chestplate_studded_steel", ArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.CHEST)));
//
//        Genesis.registry.registerContent(new RegistryObject("crop_garlic", new BaseCrop("crop_garlic")));

//        RecipeRegistry.RegisterMetalTools(ItemRegistry.nuggetCopper, ItemRegistry.ingotCopper,
//                ItemRegistry.pickaxeCopper, ItemRegistry.hoeCopper, ItemRegistry.shovelCopper, ItemRegistry.axeCopper, ItemRegistry.hammerCopper, ItemRegistry.chainCopper);

        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        //event.registerServerCommand(new TeleportCommand());
    }
}