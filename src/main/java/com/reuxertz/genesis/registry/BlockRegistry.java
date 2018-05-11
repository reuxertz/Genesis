package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.api.block.BaseBlock;
import com.reuxertz.genesis.api.block.BaseBlockMetal;
import com.reuxertz.genesis.api.block.BaseBlockOre;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class BlockRegistry {

    @GameRegistry.ObjectHolder("genesis:simpleblock2")
    public static BaseBlock simpleBlock2;
    @GameRegistry.ObjectHolder("genesis:simpleblock3")
    public static BaseBlockOre simpleBlock3;
    @GameRegistry.ObjectHolder("genesis:crop_garlic")
    public static BaseBlockOre cropGarlic;

    @GameRegistry.ObjectHolder("genesis:ore_copper")
    public static BaseBlockOre oreCopper;
    @GameRegistry.ObjectHolder("genesis:ore_tin")
    public static BaseBlockOre oreTin;
    @GameRegistry.ObjectHolder("genesis:ore_sulfur")
    public static BaseBlockOre oreSulfur;    
    @GameRegistry.ObjectHolder("genesis:ore_salt")
    public static BaseBlockOre oreSalt;      
    @GameRegistry.ObjectHolder("genesis:ore_lead")
    public static BaseBlockOre oreLead;      
    @GameRegistry.ObjectHolder("genesis:ore_silver")
    public static BaseBlockOre oreSilver;    
    @GameRegistry.ObjectHolder("genesis:ore_aluminum")
    public static BaseBlockOre oreAluminum;
    @GameRegistry.ObjectHolder("genesis:ore_nickel")
    public static BaseBlockOre oreNickel;    
    @GameRegistry.ObjectHolder("genesis:ore_zinc")
    public static BaseBlockOre oreZinc;  
    @GameRegistry.ObjectHolder("genesis:ore_sapphire")
    public static BaseBlockOre oreSapphire;
    @GameRegistry.ObjectHolder("genesis:ore_ruby")
    public static BaseBlockOre oreRuby;

    @GameRegistry.ObjectHolder("genesis:block_copper")
    public static BaseBlockMetal blockCopper;
    @GameRegistry.ObjectHolder("genesis:block_tin")
    public static BaseBlockMetal blockTin;
    @GameRegistry.ObjectHolder("genesis:block_sulfur")
    public static BaseBlockMetal blockSulfur;
    @GameRegistry.ObjectHolder("genesis:block_salt")
    public static BaseBlockMetal blockSalt;
    @GameRegistry.ObjectHolder("genesis:block_lead")
    public static BaseBlockMetal blockLead;
    @GameRegistry.ObjectHolder("genesis:block_silver")
    public static BaseBlockMetal blockSilver;
    @GameRegistry.ObjectHolder("genesis:block_aluminum")
    public static BaseBlockMetal blockAluminum;
    @GameRegistry.ObjectHolder("genesis:block_nickel")
    public static BaseBlockMetal blockNickel;
    @GameRegistry.ObjectHolder("genesis:block_zinc")
    public static BaseBlockMetal blockZinc;
    @GameRegistry.ObjectHolder("genesis:block_sapphire")
    public static BaseBlockMetal blockSapphire;
    @GameRegistry.ObjectHolder("genesis:block_ruby")
    public static BaseBlockMetal blockRuby;




}
