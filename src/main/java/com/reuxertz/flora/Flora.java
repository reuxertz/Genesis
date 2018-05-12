package com.reuxertz.flora;

import com.reuxertz.genesis.api.GenesisPlugin;
import com.reuxertz.genesis.api.IGenesisPlugin;
import com.reuxertz.genesis.api.IGenesisRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = Flora.MODID, name = Flora.NAME, version = Flora.VERSION, dependencies = "required-after:forge@[14.23.3.2655,)", useMetadata = true)
public class Flora implements IGenesisPlugin
{
    public static final String MODID = "flora";
    public static final String NAME = "Flora";
    public static final String VERSION = "1.0";

    @GenesisPlugin
    public Flora()
    {
        return;
    }

    public String getModID() { return Flora.MODID; }
    public void register(IGenesisRegistry registry)
    {

        return;
    }
}