package com.reuxertz.gaia;

import com.reuxertz.genesis.api.GenesisPlugin;
import com.reuxertz.genesis.api.IGenesisPlugin;
import com.reuxertz.genesis.api.IGenesisRegistry;
import com.reuxertz.genesis.api.block.BaseBlockOre;
import com.reuxertz.genesis.api.items.BaseItem;
import com.reuxertz.genesis.registry.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Gaia.MODID, name = Gaia.NAME, version = Gaia.VERSION, dependencies = "required-after:forge@[14.23.3.2655,)", useMetadata = true)
public class Gaia implements IGenesisPlugin
{
    public static final String MODID = "gaia";
    public static final String NAME = "Gaia";
    public static final String VERSION = "1.0";

    @GenesisPlugin
    public Gaia()
    {
        return;
    }

    public String getModID() { return Gaia.MODID; }
    public void register(IGenesisRegistry registry)
    {
        //registry.registerOre("copper");
        //registry.registerOre("tin");
//        registry.registerOre("sulfur");
//        registry.registerOre("salt");
//        registry.registerOre("lead");
//        registry.registerOre("silver");
//        registry.registerOre("aluminum");
//        registry.registerOre("nickel");
//        registry.registerOre("zinc");
//        registry.registerOre("sapphire");
//        registry.registerOre("ruby");

        //registry.registerMetalBlock("copper");   
//        registry.registerMetalBlock("tin");
//        registry.registerMetalBlock("sulfur");
//        registry.registerMetalBlock("salt");
//        registry.registerMetalBlock("lead");
//        registry.registerMetalBlock("silver");
//        registry.registerMetalBlock("aluminum");
//        registry.registerMetalBlock("nickel");
//        registry.registerMetalBlock("zinc");
//        registry.registerMetalBlock("sapphire");
//        registry.registerMetalBlock("ruby");

        //registry.registerIngot("copper");
//        registry.registerIngot("tin");
//        registry.registerIngot("lead");
//        registry.registerIngot("silver");
//        registry.registerIngot("aluminum");
//        registry.registerIngot("nickel");
//        registry.registerIngot("zinc");

        //registry.registerNugget("new BaseItem_"("copper"));    
//        registry.registerNugget("tin");
//        registry.registerNugget("lead");
//        registry.registerNugget("silver");
//        registry.registerNugget("aluminum");
//        registry.registerNugget("nickel");
//        registry.registerNugget("zinc");
//        registry.registerNugget("sapphire");
//        registry.registerNugget("ruby");

        registry.registerMetal("sulfur", false, false, false);
        registry.registerMetal("salt", false, false, false);

        registry.registerMetal("lead", false, true, false);
        registry.registerMetal("silver", false, true, false);
        registry.registerMetal("aluminum", false, true, false);
        registry.registerMetal("nickel", false, true, false);
        registry.registerMetal("zinc", false, true, false);

        registry.registerMetal("sapphire", false, false, false);
        registry.registerMetal("ruby", false, false, false);

        registry.registerMetal("copper");
        registry.registerMetal("bronze", true, true, true);
        registry.registerMetal("iron");
        registry.registerMetal("steel", true, true, true);

//        registry.registerContent("chain_iron", new BaseItem("chain_iron")));
//        registry.registerContent("chain_bronze", new BaseItem("chain_bronze")));
//        registry.registerContent("chain_steel", new BaseItem("chain_steel")));

//        registry.registerContent(new RegistryObject("sword_copper", new BaseAxe("sword_copper", ToolMaterial.COPPER_MATERIAL, 1, 1, CreativeTabs.MISC)));
//        registry.registerContent(new RegistryObject("axe_copper", new BaseAxe("axe_copper", ToolMaterial.COPPER_MATERIAL, 1, 1, CreativeTabs.MISC)));
//        registry.registerContent(new RegistryObject("hoe_copper", new BaseHoe("hoe_copper", ToolMaterial.COPPER_MATERIAL, CreativeTabs.MISC)));
//        registry.registerContent(new RegistryObject("shovel_copper", new BaseShovel("shovel_copper", ToolMaterial.COPPER_MATERIAL, CreativeTabs.MISC)));
//        registry.registerContent(new RegistryObject("pickaxe_copper", new BasePickaxe("pickaxe_copper", ToolMaterial.COPPER_MATERIAL, CreativeTabs.MISC)));
//        registry.registerContent(new RegistryObject("hammer_copper", new BaseHammer("hammer_copper", ToolMaterial.COPPER_MATERIAL, 1, 1, CreativeTabs.MISC)));
//
//        registry.registerContent(new RegistryObject("boots_copper"                , new BaseArmor("boots_copper", GenesisArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.FEET)));
//        registry.registerContent(new RegistryObject("helmet_copper"               , new BaseArmor("helmet_copper", GenesisArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.HEAD)));
//        registry.registerContent(new RegistryObject("leggings_copper"             , new BaseArmor("leggings_copper", GenesisArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.LEGS)));
//        registry.registerContent(new RegistryObject("chestplate_copper"           , new BaseArmor("chestplate_copper", GenesisArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.CHEST)));
//        registry.registerContent(new RegistryObject("boots_chain_copper"          , new BaseArmor("boots_chain_copper", GenesisArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.FEET)));
//        registry.registerContent(new RegistryObject("helmet_chain_copper"         , new BaseArmor("helmet_chain_copper", GenesisArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.HEAD)));
//        registry.registerContent(new RegistryObject("leggings_chain_copper"       , new BaseArmor("leggings_chain_copper", GenesisArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.LEGS)));
//        registry.registerContent(new RegistryObject("chestplate_chain_copper"     , new BaseArmor("chestplate_chain_copper", GenesisArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.CHEST)));
//        registry.registerContent(new RegistryObject("boots_studded_copper"        , new BaseArmor("boots_studded_copper", GenesisArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.FEET)));
//        registry.registerContent(new RegistryObject("helmet_studded_copper"       , new BaseArmor("helmet_studded_copper", GenesisArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.HEAD)));
//        registry.registerContent(new RegistryObject("leggings_studded_copper"     , new BaseArmor("leggings_studded_copper", GenesisArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.LEGS)));
//        registry.registerContent(new RegistryObject("chestplate_studded_copper"   , new BaseArmor("chestplate_studded_copper", GenesisArmorMaterial.COPPER_MATERIAL, 0, EntityEquipmentSlot.CHEST)));
//
//        registry.registerContent(new RegistryObject("sword_bronze", new BaseAxe("sword_bronze", ToolMaterial.COPPER_MATERIAL, 1, 1, CreativeTabs.MISC)));
//        registry.registerContent(new RegistryObject("axe_bronze", new BaseAxe("axe_bronze", ToolMaterial.BRONZE_MATERIAL, 1, 1, CreativeTabs.MISC)));
//        registry.registerContent(new RegistryObject("hoe_bronze", new BaseHoe("hoe_bronze", ToolMaterial.BRONZE_MATERIAL, CreativeTabs.MISC)));
//        registry.registerContent(new RegistryObject("shovel_bronze", new BaseShovel("shovel_bronze", ToolMaterial.BRONZE_MATERIAL, CreativeTabs.MISC)));
//        registry.registerContent(new RegistryObject("pickaxe_bronze", new BasePickaxe("pickaxe_bronze", ToolMaterial.BRONZE_MATERIAL, CreativeTabs.MISC)));
//        registry.registerContent(new RegistryObject("hammer_bronze", new BaseHammer("hammer_bronze", ToolMaterial.BRONZE_MATERIAL, 1, 1, CreativeTabs.MISC)));
//
//        registry.registerContent(new RegistryObject("boots_bronze", new BaseArmor("boots_bronze", GenesisArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.FEET)));
//        registry.registerContent(new RegistryObject("helmet_bronze", new BaseArmor("helmet_bronze", GenesisArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.HEAD)));
//        registry.registerContent(new RegistryObject("leggings_bronze", new BaseArmor("leggings_bronze", GenesisArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.LEGS)));
//        registry.registerContent(new RegistryObject("chestplate_bronze", new BaseArmor("chestplate_bronze", GenesisArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.CHEST)));
//        registry.registerContent(new RegistryObject("boots_chain_bronze", new BaseArmor("boots_chain_bronze", GenesisArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.FEET)));
//        registry.registerContent(new RegistryObject("helmet_chain_bronze", new BaseArmor("helmet_chain_bronze", GenesisArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.HEAD)));
//        registry.registerContent(new RegistryObject("leggings_chain_bronze", new BaseArmor("leggings_chain_bronze", GenesisArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.LEGS)));
//        registry.registerContent(new RegistryObject("chestplate_chain_bronze", new BaseArmor("chestplate_chain_bronze", GenesisArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.CHEST)));
//        registry.registerContent(new RegistryObject("boots_studded_bronze", new BaseArmor("boots_studded_bronze", GenesisArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.FEET)));
//        registry.registerContent(new RegistryObject("helmet_studded_bronze", new BaseArmor("helmet_studded_bronze", GenesisArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.HEAD)));
//        registry.registerContent(new RegistryObject("leggings_studded_bronze", new BaseArmor("leggings_studded_bronze", GenesisArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.LEGS)));
//        registry.registerContent(new RegistryObject("chestplate_studded_bronze", new BaseArmor("chestplate_studded_bronze", GenesisArmorMaterial.BRONZE_MATERIAL, 0, EntityEquipmentSlot.CHEST)));
//
//        registry.registerContent(new RegistryObject("axe_iron", new BaseAxe("axe_iron", Item.ToolMaterial.IRON, 1, 1, CreativeTabs.MISC)));
//        registry.registerContent(new RegistryObject("hoe_iron", new BaseHoe("hoe_iron", Item.ToolMaterial.IRON, CreativeTabs.MISC)));
//        registry.registerContent(new RegistryObject("shovel_iron", new BaseShovel("shovel_iron", Item.ToolMaterial.IRON, CreativeTabs.MISC)));
//        registry.registerContent(new RegistryObject("pickaxe_iron", new BasePickaxe("pickaxe_iron", Item.ToolMaterial.IRON, CreativeTabs.MISC)));
//        registry.registerContent(new RegistryObject("hammer_iron", new BaseHammer("hammer_iron", Item.ToolMaterial.IRON, 1, 1, CreativeTabs.MISC)));
//
//        registry.registerContent(new RegistryObject("boots_iron", new BaseArmor("boots_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.FEET)));
//        registry.registerContent(new RegistryObject("helmet_iron", new BaseArmor("helmet_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.HEAD)));
//        registry.registerContent(new RegistryObject("leggings_iron", new BaseArmor("leggings_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.LEGS)));
//        registry.registerContent(new RegistryObject("chestplate_iron", new BaseArmor("chestplate_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.CHEST)));
//        registry.registerContent(new RegistryObject("boots_chain_iron", new BaseArmor("boots_chain_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.FEET)));
//        registry.registerContent(new RegistryObject("helmet_chain_iron", new BaseArmor("helmet_chain_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.HEAD)));
//        registry.registerContent(new RegistryObject("leggings_chain_iron", new BaseArmor("leggings_chain_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.LEGS)));
//        registry.registerContent(new RegistryObject("chestplate_chain_iron", new BaseArmor("chestplate_chain_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.CHEST)));
//        registry.registerContent(new RegistryObject("boots_studded_iron", new BaseArmor("boots_studded_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.FEET)));
//        registry.registerContent(new RegistryObject("helmet_studded_iron", new BaseArmor("helmet_studded_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.HEAD)));
//        registry.registerContent(new RegistryObject("leggings_studded_iron", new BaseArmor("leggings_studded_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.LEGS)));
//        registry.registerContent(new RegistryObject("chestplate_studded_iron", new BaseArmor("chestplate_studded_iron", ItemArmor.ArmorMaterial.IRON, 0, EntityEquipmentSlot.CHEST)));
//
//        registry.registerContent(new RegistryObject("sword_steel", new BaseAxe("sword_steel", ToolMaterial.COPPER_MATERIAL, 1, 1, CreativeTabs.MISC)));
//        registry.registerContent(new RegistryObject("axe_steel", new BaseAxe("axe_steel", ToolMaterial.STEEL_MATERIAL, 1, 1, CreativeTabs.MISC)));
//        registry.registerContent(new RegistryObject("hoe_steel", new BaseHoe("hoe_steel", ToolMaterial.STEEL_MATERIAL, CreativeTabs.MISC)));
//        registry.registerContent(new RegistryObject("shovel_steel", new BaseShovel("shovel_steel", ToolMaterial.STEEL_MATERIAL, CreativeTabs.MISC)));
//        registry.registerContent(new RegistryObject("pickaxe_steel", new BasePickaxe("pickaxe_steel", ToolMaterial.STEEL_MATERIAL, CreativeTabs.MISC)));
//        registry.registerContent(new RegistryObject("hammer_steel", new BaseHammer("hammer_steel", ToolMaterial.STEEL_MATERIAL, 1, 1, CreativeTabs.MISC)));
//
//        registry.registerContent(new RegistryObject("boots_steel", new BaseArmor("boots_steel", GenesisArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.FEET)));
//        registry.registerContent(new RegistryObject("helmet_steel", new BaseArmor("helmet_steel", GenesisArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.HEAD)));
//        registry.registerContent(new RegistryObject("leggings_steel", new BaseArmor("leggings_steel", GenesisArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.LEGS)));
//        registry.registerContent(new RegistryObject("chestplate_steel", new BaseArmor("chestplate_steel", GenesisArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.CHEST)));
//        registry.registerContent(new RegistryObject("boots_chain_steel", new BaseArmor("boots_chain_steel", GenesisArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.FEET)));
//        registry.registerContent(new RegistryObject("helmet_chain_steel", new BaseArmor("helmet_chain_steel", GenesisArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.HEAD)));
//        registry.registerContent(new RegistryObject("leggings_chain_steel", new BaseArmor("leggings_chain_steel", GenesisArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.LEGS)));
//        registry.registerContent(new RegistryObject("chestplate_chain_steel", new BaseArmor("chestplate_chain_steel", GenesisArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.CHEST)));
//        registry.registerContent(new RegistryObject("boots_studded_steel", new BaseArmor("boots_studded_steel", GenesisArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.FEET)));
//        registry.registerContent(new RegistryObject("helmet_studded_steel", new BaseArmor("helmet_studded_steel", GenesisArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.HEAD)));
//        registry.registerContent(new RegistryObject("leggings_studded_steel", new BaseArmor("leggings_studded_steel", GenesisArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.LEGS)));
//        registry.registerContent(new RegistryObject("chestplate_studded_steel", new BaseArmor("chestplate_studded_steel", GenesisArmorMaterial.STEEL_MATERIAL, 0, EntityEquipmentSlot.CHEST)));
//
//        RecipeRegistry.RegisterMetalTools(ItemRegistry.nuggetCopper, ItemRegistry.ingotCopper,
//                ItemRegistry.pickaxeCopper, ItemRegistry.hoeCopper, ItemRegistry.shovelCopper, ItemRegistry.axeCopper, ItemRegistry.hammerCopper, ItemRegistry.chainCopper);
//
//        logger = event.getModLog();

        return;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {

    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        //event.registerServerCommand(new TeleportCommand());
    }
}