package com.reuxertz.genesisAPI;

import com.reuxertz.genesis.mod.Genesis;
import com.reuxertz.genesis.mod.GenesisApiHandler;
import com.reuxertz.genesis.handlers.ForgeHandler;
import com.reuxertz.genesis.handlers.NetworkHandler;
import com.reuxertz.genesis.registry.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;
import java.util.List;

@Mod(modid = GenesisAPI.MODID, name = GenesisAPI.NAME, version = GenesisAPI.VERSION, dependencies = "required-after:forge@[14.23.3.2655,)", useMetadata = true)
@Mod.EventBusSubscriber(modid = GenesisAPI.MODID)
public class GenesisAPI {
    public static final String MODID = "genesisapi";
    public static final String NAME = "Genesis API";
    public static final String VERSION = "1.0";

    @Mod.Instance
    public static GenesisAPI instance;
    public static List<IGenesisPlugin> plugins = new ArrayList();
    public static GenesisRegistry registry = new GenesisRegistry();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        OBJLoader.INSTANCE.addDomain(MODID);
        GenesisApiHandler.loadPlugins(event.getAsmData());
        MinecraftForge.EVENT_BUS.register(new ForgeHandler());
        NetworkHandler.preInit();
    }
}