package com.reuxertz.fauna;

import com.reuxertz.genesis.mod.Genesis;
import com.reuxertz.genesis.mod.GenesisApiHandler;
import com.reuxertz.genesis.proxy.CommonProxy;
import com.reuxertz.genesis.registry.GenesisRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.FMLModContainer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Fauna.MODID)
public class ClientProxy
{
    public RenderManager renderManager;
    public RenderItem renderItem;
    public ItemModelMesher itemModelMesher;

    public void preInit(FMLPreInitializationEvent event) {

        Genesis.registry.registerEntityRenderers(Fauna.MODID);
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {


        renderManager = Minecraft.getMinecraft().getRenderManager();
        renderItem = Minecraft.getMinecraft().getRenderItem();
        itemModelMesher = renderItem.getItemModelMesher();

        ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(resourceManager -> {
            GenesisApiHandler.register();
            Genesis.logger.info("Reloaded API");
        });
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        GenesisRegistry.initModels(Fauna.MODID);
    }
}
