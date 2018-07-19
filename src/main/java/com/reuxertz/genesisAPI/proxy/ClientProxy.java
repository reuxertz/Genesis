package com.reuxertz.genesisAPI.proxy;

import com.reuxertz.genesisAPI.GenesisAPI;
import com.reuxertz.genesisAPI.internal.GenesisApiHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = GenesisAPI.MODID)
public class ClientProxy extends CommonProxy {
    public RenderManager renderManager;
    public RenderItem renderItem;
    public ItemModelMesher itemModelMesher;

    @Override
    public void preInit(FMLPreInitializationEvent event) {

        super.preInit(event);
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

        ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(resourceManager -> {
            GenesisApiHandler.register();
            GenesisAPI.logger.info("Reloaded API");
        });
    }
}