package com.reuxertz.genesisAPI;

import com.reuxertz.genesisAPI.proxy.CommonProxy;
import com.reuxertz.genesisAPI.registry.*;
import com.reuxertz.genesisAPI.internal.GenesisApiHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = GenesisAPI.MODID, name = GenesisAPI.NAME, version = GenesisAPI.VERSION, dependencies = "required-after:forge@[14.23.3.2655,)", useMetadata = true)
@Mod.EventBusSubscriber(modid = GenesisAPI.MODID)
public class GenesisAPI {
    public static final String MODID = "genesisapi";
    public static final String NAME = "Genesis API";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "com.reuxertz.genesisAPI.proxy.ClientProxy", serverSide = "com.reuxertz.genesisAPI.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static GenesisAPI instance;
    public static GenesisRegistry registry = new GenesisRegistry();
    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){

        OBJLoader.INSTANCE.addDomain(MODID);
        GenesisApiHandler.loadPlugins(event.getAsmData());

        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}