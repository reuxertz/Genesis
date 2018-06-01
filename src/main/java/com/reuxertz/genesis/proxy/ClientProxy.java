package com.reuxertz.genesis.proxy;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.entities.EntityHuman;
import com.reuxertz.genesis.internal.GenesisApiHandler;
import com.reuxertz.genesis.registry.GenesisRegistry;
import com.reuxertz.genesis.registry.RenderRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Genesis.MODID)
public class ClientProxy extends CommonProxy {
    public RenderManager renderManager;
    public RenderItem renderItem;
    public ItemModelMesher itemModelMesher;

    @Override
    public void preInit(FMLPreInitializationEvent event) {

        super.preInit(event);

        RenderingRegistry.registerEntityRenderingHandler(EntityHuman.class, RenderRegistry.RenderFactoryEntityHuman.INSTANCE);
    }

    @Override
    public void init(FMLInitializationEvent event) {

        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

        super.postInit(event);

        renderManager = Minecraft.getMinecraft().getRenderManager();
        renderItem = Minecraft.getMinecraft().getRenderItem();
        itemModelMesher = renderItem.getItemModelMesher();

        registerEntityRenderers();
        registerObjRegRenderers();

        ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(resourceManager -> {
            GenesisApiHandler.register();
            Genesis.logger.info("Reloaded API");
        });
    }

    // Protected Functions
    protected void registerObjRegRenderers() {
    }

    protected void registerEntityRenderers() {

    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        GenesisRegistry.initModels();
    }

}