package com.reuxertz.gaia;

import com.reuxertz.genesis.api.GenesisPlugin;
import com.reuxertz.genesis.api.IGenesisPlugin;
import com.reuxertz.genesis.api.IGenesisRegistry;
import net.minecraftforge.fml.common.Mod;

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
        registry.registerMetal("sulfur", false, false, false).autoRegister();
        registry.registerMetal("salt", false, false, false).autoRegister();

        registry.registerMetal("lead", false, true, false).autoRegister();
        registry.registerMetal("silver", false, true, false).autoRegister();
        registry.registerMetal("aluminum", false, true, false).autoRegister();
        registry.registerMetal("nickel", false, true, false).autoRegister();
        registry.registerMetal("zinc", false, true, false).autoRegister();

        registry.registerMetal("sapphire", false, false, false).autoRegister();
        registry.registerMetal("ruby", false, false, false).autoRegister();

        registry.registerMetal("copper").autoRegister();
        registry.registerMetal("bronze", true, true, true).autoRegister();
        registry.registerMetal("iron").autoRegister();
        registry.registerMetal("steel", true, true, true).autoRegister();

        return;
    }
}