package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.block.base.BlockBase;
import com.reuxertz.genesis.block.base.IBaseBlock;
import com.reuxertz.genesis.items.base.IItemBase;
import com.reuxertz.genesis.mod.Genesis;
import com.reuxertz.genesis.render.RenderGenesisLiving;
import com.reuxertz.genesis.tileentities.TileEntityCropBase;
import com.reuxertz.genesisAPI.registry.GenesisRegistry;
import com.reuxertz.genesisAPI.registry.RegistryObject;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AutoRegistryHelper {

    public static void registerModItems(RegistryEvent.Register<Item> event, String modId) {

        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++) {

            RegistryObject regobj = GenesisRegistry.registryObjectList.get(i);

            if (modId != null && regobj.autoRegister)
                continue;

            if (modId == null && !regobj.autoRegister)
                continue;

            if (modId != null && regobj.modId != modId)
                continue;

            if (regobj.block != null && regobj.item == null &&
                    regobj.block instanceof BlockBase && ((BlockBase)regobj.block).isSimple)
            {
                Block block = (Block)regobj.block;

                ItemBlock bl = new ItemBlock(block);
                bl.setRegistryName(block.getUnlocalizedName());
                bl.setUnlocalizedName(block.getUnlocalizedName());
                regobj.item = bl;
            }

            if (regobj.item != null)
                event.getRegistry().register(regobj.item);
        }

        return;
    }

    public static void registerModBlocks(RegistryEvent.Register<Block> event, String modId) {

        if (modId == null)
            GameRegistry.registerTileEntity(TileEntityCropBase.class, Genesis.MODID + ":tileEntityCropBase");

        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++) {
            RegistryObject regobj = GenesisRegistry.registryObjectList.get(i);

            if (modId != null && regobj.autoRegister)
                continue;

            if (modId == null && !regobj.autoRegister)
                continue;

            if (modId != null && regobj.modId != modId)
                continue;

            if (regobj.block != null) {
                if (regobj.isBlockRegistered())
                    continue;

                event.getRegistry().register((Block) regobj.block);

                if (regobj.tileEntityClass == null)
                    continue;

                String name = regobj.tileEntityClass.getSimpleName().toLowerCase();
                GameRegistry.registerTileEntity(regobj.tileEntityClass, regobj.modId + ":" + name);
            }
        }
    }

    public static void registerModEntities(RegistryEvent.Register<EntityEntry> event, String modId) {
        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++) {
            RegistryObject regobj = GenesisRegistry.registryObjectList.get(i);

            if (modId != null && regobj.autoRegister)
                continue;

            if (modId == null && !regobj.autoRegister)
                continue;

            if (modId != null && regobj.modId != modId)
                continue;

            if (regobj.entityEntry != null)
                event.getRegistry().register(regobj.entityEntry);
        }
        return;
    }

    @SuppressWarnings("unchecked")
    public static void registerEntityRenderers(String modId)
    {
        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++)
        {
            RegistryObject regobj = GenesisRegistry.registryObjectList.get(i);

            if (modId != null && regobj.autoRegister)
                continue;

            if (modId == null && !regobj.autoRegister)
                continue;

            if (modId != null && regobj.modId != modId)
                continue;

            if (regobj.entityEntry != null)
            {
                RenderingRegistry.registerEntityRenderingHandler(regobj.entityEntry.getEntityClass(),
                        manager -> {
                            RenderGenesisLiving renderGenesisLiving = new RenderGenesisLiving(manager, regobj.name, regobj.entityModel, 0.1f) {
                                @Override
                                protected ResourceLocation getEntityTexture(Entity entity) {
                                    return new ResourceLocation(regobj.modId + ":" + "textures/entities/" + regobj.name + "/" + regobj.name + ".png");
                                }
                            };
                            //regobj.setRender(renderGenesisLiving);
                            return renderGenesisLiving;
                        }
                );
            }
        }
    }
    public static void initModels(String modId)
    {
        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++) {

            RegistryObject regobj = GenesisRegistry.registryObjectList.get(i);

            if (modId != null && regobj.autoRegister)
                continue;

            if (modId == null && !regobj.autoRegister)
                continue;

            if (modId != null && regobj.modId != modId)
                continue;

            initModel(GenesisRegistry.registryObjectList.get(i));
        }

        return;
    }

    public static void initModel(RegistryObject registryObject)
    {
        if (registryObject.isModelInitialized())
            return;

        if (registryObject.item != null && registryObject.item instanceof IItemBase)
            ((IItemBase)registryObject.item).initModel();

        if (registryObject.block != null && registryObject.block instanceof IBaseBlock)
            ((IBaseBlock)registryObject.block).initModel();

        registryObject.isModelInitialized = true;
    }

}
