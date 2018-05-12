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
    public static final String NAME = "Flora";
    public static final String VERSION = "1.0";

    @GenesisPlugin
    public Gaia()
    {
        return;
    }

    public String getModID() { return Gaia.MODID; }
    public void register(IGenesisRegistry registry)
    {
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

        return;
    }
}