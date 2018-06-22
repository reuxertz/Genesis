package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.api.blocks.IBaseBlock;
import com.reuxertz.genesis.api.items.IBaseItem;
import com.reuxertz.genesis.render.LayerGenesisLiving;
import com.reuxertz.genesis.render.RenderGenesisLiving;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import sun.plugin.javascript.navig4.Layer;

import java.util.HashMap;
import java.util.Map;

public class RegistryObject {

    public static class LayerResourceLocation extends ResourceLocation
    {
        public int zIndex;

        public LayerResourceLocation(String modID, String resourcePath, int zIndex)
        {
            super(modID, resourcePath);
            this.zIndex = zIndex;
        }
    }

    public String name;
    public String modId;
    public boolean autoRegister = false;

    public EntityEntry entityEntry;
    public Map<String, LayerResourceLocation> entityLayerResourceMap = new HashMap<>();
    public RenderGenesisLiving renderGenesisLiving;
    public ModelBase entityModel;
    public ModelResourceLocation entityModelResourceLocation;

    public Block block;
    public Class tileEntityClass;

    public Item item;

    protected boolean _isItemRegistered;
    protected boolean _isBlockRegistered;
    protected boolean _isModelInitialized;
    protected boolean _isEntityRegistered;

    public boolean isItemRegistered()
    {
        return _isItemRegistered;
    }
    public boolean isBlockRegistered()
    {
        return _isBlockRegistered;
    }
    public boolean isModelInitialized()
    {
        return _isModelInitialized;
    }

    public String getUnlocalizedString()
    {
        return modId + "." + name;
    }

    public void setRender(RenderGenesisLiving renderGenesisLiving) { this.renderGenesisLiving = renderGenesisLiving; }

    protected RegistryObject(String modId, String name) {
        this.modId = modId;
        this.name = name;
    }
    public RegistryObject(String modId, String name, Item item)
    {
        this(modId, name);
        this.item = item;

        item.setUnlocalizedName(getUnlocalizedString());
        item.setRegistryName(modId, name);
    }
    public RegistryObject(String modId, String name, Block block)
    {
        this(modId, name);
        this.block = block;
        block.setUnlocalizedName(getUnlocalizedString());
        block.setRegistryName(modId, name);
    }
    public RegistryObject(String modId, String name, Block block, Class tileEntityClass)
    {
        this(modId, name, block);
        this.tileEntityClass = tileEntityClass;
    }
    public RegistryObject(String modId, String name, EntityEntry entry, ModelBase modelbase)
    {
        this(modId, name);
        this.entityEntry = entry;
        this.entityModel = modelbase;
    }
    public RegistryObject(String modId, String name, EntityEntry entry, ModelResourceLocation modelResourceLocation)
    {
        this(modId, name);
        this.entityEntry = entry;
        this.entityModelResourceLocation = modelResourceLocation;
    }

    public void autoRegister(boolean autoRegister)
    {
        this.autoRegister = autoRegister;
    }

    public void initModel()
    {
        if (isModelInitialized())
            return;

        if (item != null && item instanceof IBaseItem)
            ((IBaseItem)item).initModel();

        if (block != null && block instanceof IBaseBlock)
            ((IBaseBlock)block).initModel();

        _isModelInitialized = true;
    }
}
