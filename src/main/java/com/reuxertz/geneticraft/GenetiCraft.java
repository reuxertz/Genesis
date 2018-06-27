package com.reuxertz.geneticraft;
import com.reuxertz.genesis.api.GenesisPlugin;
import com.reuxertz.genesis.api.IGenesisPlugin;
import com.reuxertz.genesis.api.IGenesisRegistry;
import net.minecraftforge.fml.common.Mod;

//@Mod(
//        modid = com.reuxertz.geneticraft.GenetiCraft.MODID,
//        name = com.reuxertz.geneticraft.GenetiCraft.NAME,
//        version = com.reuxertz.geneticraft.GenetiCraft.VERSION,
//        dependencies = "required-after:forge@[14.23.3.2655,)", useMetadata = true)

public class GenetiCraft implements IGenesisPlugin
{
    public static final String MODID = "geneticraft";
    public static final String NAME = "GenetiCraft";
    public static final String VERSION = "1.0";

    //@GenesisPlugin
    public GenetiCraft()
    {
        return;
    }

    public String getModID() { return com.reuxertz.geneticraft.GenetiCraft.MODID; }
    public void register(IGenesisRegistry registry)
    {
        //registry.registerItem("syringe").autoRegister();

        return;
    }
}
