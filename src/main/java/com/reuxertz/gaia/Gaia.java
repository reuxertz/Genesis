package com.reuxertz.gaia;

import com.reuxertz.genesis.api.GenesisPlugin;
import com.reuxertz.genesis.api.IGenesisPlugin;
import com.reuxertz.genesis.api.IGenesisRegistry;
import com.reuxertz.genesis.util.RegistryHelper;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = Gaia.MODID, name = Gaia.NAME, version = Gaia.VERSION, dependencies = "required-after:forge@[14.23.3.2655,)", useMetadata = true)
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
        RegistryHelper.registerMetal("sulfur", getModID(), false, false, false).autoRegister();
        RegistryHelper.registerMetal("salt", getModID(), false, false, false).autoRegister();

        RegistryHelper.registerMetal("lead", getModID(), false, true, false).autoRegister();
        RegistryHelper.registerMetal("silver", getModID(), false, true, false).autoRegister();
        RegistryHelper.registerMetal("aluminum", getModID(), false, true, false).autoRegister();
        RegistryHelper.registerMetal("nickel", getModID(), false, true, false).autoRegister();
        RegistryHelper.registerMetal("zinc", getModID(), false, true, false).autoRegister();

        RegistryHelper.registerMetal("sapphire", getModID(), false, false, false).autoRegister();
        RegistryHelper.registerMetal("ruby", getModID(), false, false, false).autoRegister();

        RegistryHelper.registerMetal("copper", getModID()).autoRegister();
        RegistryHelper.registerMetal("bronze", getModID(), true, true, true).autoRegister();
        RegistryHelper.registerMetal("iron", getModID()).autoRegister();
        RegistryHelper.registerMetal("steel", getModID(), true, true, true).autoRegister();

        return;
    }
}