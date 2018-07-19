package com.reuxertz.gaia;

import com.reuxertz.genesisAPI.IGenesisPlugin;
import com.reuxertz.genesisAPI.registry.IGenesisRegistry;
import com.reuxertz.genesis.registry.MultiRegistryHelper;

//@Mod(modid = Gaia.MODID, name = Gaia.NAME, version = Gaia.VERSION, dependencies = "required-after:forge@[14.23.3.2655,)", useMetadata = true)
public class Gaia implements IGenesisPlugin
{
    public static final String MODID = "gaia";
    public static final String NAME = "Gaia";
    public static final String VERSION = "1.0";

    //@GenesisPlugin
    public Gaia()
    {
        return;
    }

    public String getModID() { return Gaia.MODID; }
    public void register(IGenesisRegistry registry)
    {
        MultiRegistryHelper.registerMetal("sulfur", getModID(), false, false, false).autoRegister();
        MultiRegistryHelper.registerMetal("salt", getModID(), false, false, false).autoRegister();

        MultiRegistryHelper.registerMetal("lead", getModID(), false, true, false).autoRegister();
        MultiRegistryHelper.registerMetal("silver", getModID(), false, true, false).autoRegister();
        MultiRegistryHelper.registerMetal("aluminum", getModID(), false, true, false).autoRegister();
        MultiRegistryHelper.registerMetal("nickel", getModID(), false, true, false).autoRegister();
        MultiRegistryHelper.registerMetal("zinc", getModID(), false, true, false).autoRegister();

        MultiRegistryHelper.registerMetal("sapphire", getModID(), false, false, false).autoRegister();
        MultiRegistryHelper.registerMetal("ruby", getModID(), false, false, false).autoRegister();

        MultiRegistryHelper.registerMetal("copper", getModID()).autoRegister();
        MultiRegistryHelper.registerMetal("bronze", getModID(), true, true, true).autoRegister();
        MultiRegistryHelper.registerMetal("iron", getModID()).autoRegister();
        MultiRegistryHelper.registerMetal("steel", getModID(), true, true, true).autoRegister();

        return;
    }
}