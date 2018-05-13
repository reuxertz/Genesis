package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.api.block.IBaseBlock;
import com.reuxertz.genesis.api.items.IBaseItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.EntityEntry;

public class RegistryObject {

    public EntityEntry entityEntry;
    public String name;
    public String modId;
    public Block block;
    public Item item;
    public Class tileEntityClass;

    protected boolean _isItemRegistered;
    protected boolean _isBlockRegistered;
    protected boolean _isModelInitialized;
    protected boolean _isEntityRegistered;

    protected boolean _hasItemBlock;

    public boolean hasItemBlock()
    {
        return _hasItemBlock;
    }

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
    public RegistryObject(String modId, String name, EntityEntry entry)
    {
        this(modId, name);
        this.entityEntry = entry;
    }

    public RegistryObject hasItemBlock(boolean hasItemBlock)
    {
        _hasItemBlock = hasItemBlock;
        return this;
    }

    public void registerItem()
    {
        _isItemRegistered = true;
    }
    public void registerBlock()
    {
        _isBlockRegistered = true;
    }
    public void registerEntity()
    {
        _isEntityRegistered = true;
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
