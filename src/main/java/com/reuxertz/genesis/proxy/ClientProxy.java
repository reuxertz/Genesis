package com.reuxertz.genesis.proxy;

import com.reuxertz.genesis.mod.*;
import com.reuxertz.genesis.registry.GenesisRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Genesis.MODID)
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
            Genesis.logger.info("Reloaded API");
        });

        Genesis.registry.iterate((regObj) ->
        {
            if (regObj.item instanceof IItemColor)
                Minecraft.getMinecraft().getItemColors().registerItemColorHandler((IItemColor)regObj.item, regObj.item);
        });

//        items.registerItemColorHandler(new IItemColor()
//        {
//            @Override
//            public int getColorFromItemstack(ItemStack stack, int tintIndex) {
//                Item item = stack.getItem();
//                if (item instanceof IColorable)
//                {
//                    return ((IColorable) item).getColorFromItemStack(stack, tintIndex);
//                }
//                else
//                {
//                    return 0xFFFFFF;
//                }
//            }
//        }, ModItems.enderbag);
    }
}