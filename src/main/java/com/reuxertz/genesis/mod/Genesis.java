package com.reuxertz.genesis.mod;

import com.reuxertz.genesis.block.BlockGenesisGrass;
import com.reuxertz.genesis.block.base.BlockContainerBase;
import com.reuxertz.genesis.items.EntitySpawnEgg;
import com.reuxertz.genesis.items.base.ItemBase;
import com.reuxertz.genesis.items.base.ItemContainerBase;
import com.reuxertz.genesis.tileentities.TileEntityContainerBase;
import com.reuxertz.genesisAPI.GenesisPlugin;
import com.reuxertz.genesisAPI.IGenesisPlugin;
import com.reuxertz.genesisAPI.internal.GenesisAPIHandler;
import com.reuxertz.genesis.command.GenesisCommand;
import com.reuxertz.genesis.handlers.ForgeHandler;
import com.reuxertz.genesis.handlers.GuiHandler;
import com.reuxertz.genesis.handlers.NetworkHandler;
import com.reuxertz.genesis.proxy.CommonProxy;
import com.reuxertz.genesisAPI.registry.IGenesisRegistry;
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

import static com.reuxertz.genesis.registry.AutoRegistryHelper.*;

@Mod(modid = Genesis.MODID, name = Genesis.NAME, version = Genesis.VERSION, dependencies = "required-after:forge@[14.23.3.2655,)", useMetadata = true)
@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class Genesis implements IGenesisPlugin
{
    public static final String MODID = "genesis";
    public static final String NAME = "Genesis";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "com.reuxertz.genesis.proxy.ClientProxy", serverSide = "com.reuxertz.genesis.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static Genesis instance;
    public static SimpleNetworkWrapper networkInstance = NetworkRegistry.INSTANCE.newSimpleChannel(Genesis.MODID);
    public static Logger logger;

    @Override
    public String getModID() {
        return Genesis.MODID;
    }

    @GenesisPlugin
    public Genesis()
    {

    }

    @Override
    public void register(IGenesisRegistry registry) {

        registry.registerItem("entity_spawn_egg", Genesis.MODID, new EntitySpawnEgg()).autoRegister();

        registry.registerBlock("block_table_test", Genesis.MODID, new BlockContainerBase("block_table_test"), TileEntityContainerBase.class).autoRegister();

        registry.registerBlock("genesis_grass", Genesis.MODID, new BlockGenesisGrass("genesis_grass")).autoRegister();

        registry.registerItem("coin", Genesis.MODID, new ItemBase("coin")).autoRegister();
        registry.registerItem("item_sack", Genesis.MODID, new ItemContainerBase("item_sack")).autoRegister();

        return;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){

        OBJLoader.INSTANCE.addDomain(MODID);
        GenesisAPIHandler.loadPlugins(event.getAsmData());
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

        registerEntityRenderers(null);
        initModels(null);
    }
}