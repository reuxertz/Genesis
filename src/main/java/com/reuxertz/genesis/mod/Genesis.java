package com.reuxertz.genesis.mod;

import com.reuxertz.genesis.api.IGenesisPlugin;
import com.reuxertz.genesis.command.GenesisCommand;
import com.reuxertz.genesis.handlers.ForgeHandler;
import com.reuxertz.genesis.handlers.GuiHandler;
import com.reuxertz.genesis.handlers.NetworkHandler;
import com.reuxertz.genesis.proxy.CommonProxy;
import com.reuxertz.genesis.registry.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

//@Mod(modid = Genesis.MODID, name = Genesis.NAME, version = Genesis.VERSION, dependencies = "required-after:forge@[14.23.3.2655,)", useMetadata = true)
public class Genesis
{
    public static final String MODID = "genesis";
    public static final String NAME = "Genesis";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "com.reuxertz.genesis.proxy.ClientProxy", serverSide = "com.reuxertz.genesis.proxy.ServerProxy")
    public static CommonProxy proxy;

    //@Mod.Instance
    //public static List<IGenesisPlugin> plugins = new ArrayList();
    public static GenesisRegistry registry = new GenesisRegistry();
    public static SimpleNetworkWrapper networkInstance = NetworkRegistry.INSTANCE.newSimpleChannel(Genesis.MODID);
    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){

        OBJLoader.INSTANCE.addDomain(MODID);
        //GenesisApiHandler.loadPlugins(event.getAsmData());
        MinecraftForge.EVENT_BUS.register(new ForgeHandler());
        NetworkHandler.preInit();

        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new GenesisCommand());
    }
}