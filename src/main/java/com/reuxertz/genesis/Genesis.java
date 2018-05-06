package com.reuxertz.genesis;

import com.reuxertz.genesis.block.SimpleBlock;
import com.reuxertz.genesis.block.SimpleCrop;
import com.reuxertz.genesis.item.SimpleItem;
import com.reuxertz.genesis.proxy.CommonProxy;
import com.reuxertz.genesis.registry.GenesisRegistry;
import com.reuxertz.genesis.registry.RegistryObject;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Genesis.MODID, name = Genesis.NAME, version = Genesis.VERSION, dependencies = "required-after:forge@[14.23.3.2655,)", useMetadata = true)
public class Genesis
{
    public static final String MODID = "genesis";
    public static final String NAME = "Genesis";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "com.reuxertz.genesis.proxy.ClientProxy", serverSide = "com.reuxertz.genesis.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static Genesis instance;
    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){

        GenesisRegistry.registerContent(new RegistryObject("simpleitem", new SimpleItem("simpleitem2")));

        GenesisRegistry.registerContent(new RegistryObject("simpleblock2", new SimpleBlock("simpleblock2")));
        GenesisRegistry.registerContent(new RegistryObject("simpleblock3", new SimpleBlock("simpleblock3")));

        GenesisRegistry.registerContent(new RegistryObject("ore_copper", new SimpleBlock("ore_copper")));

        GenesisRegistry.registerContent(new RegistryObject("crop_garlic", new SimpleCrop("crop_garlic")));

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

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        //event.registerServerCommand(new TeleportCommand());
    }
}