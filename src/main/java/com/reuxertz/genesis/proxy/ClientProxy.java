package com.reuxertz.genesis.proxy;

import com.reuxertz.genesis.mod.*;
import com.reuxertz.genesis.registry.GenesisRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraftforge.client.event.ModelRegistryEvent;
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

        Genesis.registry.registerEntityRenderers();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        //GenesisRegistry.registerEntityLayerRenderers();


//        for (int i = 0; i < Genesis.registry.)
//            for (Map.Entry<String, ResourceLocation> entry : regobj.entityLayerResourceMap.entrySet() ) {
//                String key = entry.getKey();
//                ResourceLocation resourceLocation = entry.getValue();
//            }

        int a = 8;
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