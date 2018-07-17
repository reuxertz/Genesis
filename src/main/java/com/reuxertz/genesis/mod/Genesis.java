package com.reuxertz.genesis.mod;

import com.reuxertz.genesisAPI.IGenesisPlugin;
import com.reuxertz.genesis.command.GenesisCommand;
import com.reuxertz.genesis.handlers.ForgeHandler;
import com.reuxertz.genesis.handlers.GuiHandler;
import com.reuxertz.genesis.handlers.NetworkHandler;
import com.reuxertz.genesis.proxy.CommonProxy;
import com.reuxertz.genesis.registry.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityEntry;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.reuxertz.genesis.registry.GenesisRegistry.registerModBlocks;
import static com.reuxertz.genesis.registry.GenesisRegistry.registerModEntities;
import static com.reuxertz.genesis.registry.GenesisRegistry.registerModItems;

@Mod(modid = Genesis.MODID, name = Genesis.NAME, version = Genesis.VERSION, dependencies = "required-after:forge@[14.23.3.2655,)", useMetadata = true)
@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class Genesis
{
    public static final String MODID = "genesis";
    public static final String NAME = "Genesis";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "com.reuxertz.genesis.proxy.ClientProxy", serverSide = "com.reuxertz.genesis.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static Genesis instance;
    public static List<IGenesisPlugin> plugins = new ArrayList();
    public static GenesisRegistry registry = new GenesisRegistry();
    public static SimpleNetworkWrapper networkInstance = NetworkRegistry.INSTANCE.newSimpleChannel(Genesis.MODID);
    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){

        OBJLoader.INSTANCE.addDomain(MODID);
        GenesisApiHandler.loadPlugins(event.getAsmData());
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

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        registerModItems(event, null);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        registerModBlocks(event, null);
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event)
    {
        registerModEntities(event, null);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {

        GenesisRegistry.registerEntityRenderers(null);
        GenesisRegistry.initModels(null);
    }
}