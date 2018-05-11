package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.api.items.BaseIngot;
import com.reuxertz.genesis.api.items.BaseNugget;
import com.reuxertz.genesis.api.items.tools.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class ItemRegistry {

    //Mod Items
//    @GameRegistry.ObjectHolder("genesis:simpleitem")
//    public static SimpleItem simpleItem;
//    @GameRegistry.ObjectHolder("genesis:simpleitem2")
//    public static SimpleItem simpleItem2;



    @GameRegistry.ObjectHolder("genesis:ingot_copper")
    public static BaseIngot ingotCopper;
    @GameRegistry.ObjectHolder("genesis:ingot_tin")
    public static BaseIngot ingotTin;
    @GameRegistry.ObjectHolder("genesis:ingot_lead")
    public static BaseIngot ingotLead;
    @GameRegistry.ObjectHolder("genesis:ingot_silver")
    public static BaseIngot ingotSilver;
    @GameRegistry.ObjectHolder("genesis:ingot_aluminum")
    public static BaseIngot ingotAluminum;
    @GameRegistry.ObjectHolder("genesis:ingot_nickel")
    public static BaseIngot ingotNickel;
    @GameRegistry.ObjectHolder("genesis:ingot_zinc")
    public static BaseIngot ingotZinc;

    @GameRegistry.ObjectHolder("genesis:nugget_copper")
    public static BaseNugget nuggetCopper;
    @GameRegistry.ObjectHolder("genesis:nugget_tin")
    public static BaseNugget nuggetTin;
    @GameRegistry.ObjectHolder("genesis:nugget_lead")
    public static BaseNugget nuggetLead;
    @GameRegistry.ObjectHolder("genesis:nugget_silver")
    public static BaseNugget nuggetSilver;
    @GameRegistry.ObjectHolder("genesis:nugget_aluminum")
    public static BaseNugget nuggetAluminum;
    @GameRegistry.ObjectHolder("genesis:nugget_nickel")
    public static BaseNugget nuggetNickel;
    @GameRegistry.ObjectHolder("genesis:nugget_zinc")
    public static BaseNugget nuggetZinc;
    @GameRegistry.ObjectHolder("genesis:nugget_sapphire")
    public static BaseNugget nuggetSapphire;
    @GameRegistry.ObjectHolder("genesis:nugget_ruby")
    public static BaseNugget nuggetRuby;


    @GameRegistry.ObjectHolder("genesis:chain_copper")
    public static BaseNugget chainCopper;
    @GameRegistry.ObjectHolder("genesis:chain_bronze")
    public static BaseNugget chainBronze;
    @GameRegistry.ObjectHolder("genesis:chain_iron")
    public static BaseNugget chainIron;
    @GameRegistry.ObjectHolder("genesis:chain_steel")
    public static BaseNugget chainSteel;


    @GameRegistry.ObjectHolder("genesis:sword_copper")
    public static BaseSword swordCopper;
    @GameRegistry.ObjectHolder("genesis:sword_copper")
    public static BaseAxe axeCopper;
    @GameRegistry.ObjectHolder("genesis:sword_copper")
    public static BaseShovel shovelCopper;
    @GameRegistry.ObjectHolder("genesis:sword_copper")
    public static BasePickaxe pickaxeCopper;
    @GameRegistry.ObjectHolder("genesis:sword_copper")
    public static BaseHoe hoeCopper;
    @GameRegistry.ObjectHolder("genesis:hammer_copper")
    public static BaseHammer hammerCopper;
    
    @GameRegistry.ObjectHolder("genesis:sword_bronze")
    public static BaseSword swordBronze;
    @GameRegistry.ObjectHolder("genesis:sword_bronze")
    public static BaseAxe axeBronze;
    @GameRegistry.ObjectHolder("genesis:sword_bronze")
    public static BaseShovel shovelBronze;
    @GameRegistry.ObjectHolder("genesis:sword_bronze")
    public static BasePickaxe pickaxeBronze;
    @GameRegistry.ObjectHolder("genesis:sword_bronze")
    public static BaseHoe hoeBronze;
    @GameRegistry.ObjectHolder("genesis:hammer_bronze")
    public static BaseHammer hammerBronze;

    @GameRegistry.ObjectHolder("genesis:hammer_iron")
    public static BaseHammer hammerIron;

    @GameRegistry.ObjectHolder("genesis:sword_steel")
    public static BaseSword swordSteel;
    @GameRegistry.ObjectHolder("genesis:sword_steel")
    public static BaseAxe axeSteel;
    @GameRegistry.ObjectHolder("genesis:sword_steel")
    public static BaseShovel shovelSteel;
    @GameRegistry.ObjectHolder("genesis:sword_steel")
    public static BasePickaxe pickaxeSteel;
    @GameRegistry.ObjectHolder("genesis:sword_steel")
    public static BaseHoe hoeSteel;
    @GameRegistry.ObjectHolder("genesis:hammer_steel")
    public static BaseHammer hammerSteel;
}
