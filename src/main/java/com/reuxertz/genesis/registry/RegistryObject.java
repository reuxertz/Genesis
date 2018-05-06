package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.api.block.BaseBlock;
import com.reuxertz.genesis.api.item.BaseItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class RegistryObject {

    public String name;
    public Block block;
    public Item item;

    protected boolean _isItemRegistered;
    protected boolean _isBlockRegistered;
    protected boolean _isModelInitialized;

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

    protected RegistryObject(String name)
    {
        this.name = name;
    }
    public RegistryObject(String name, BaseItem item)
    {
        this(name);
        this.item = item;
    }
    public RegistryObject(String name, BaseBlock block)
    {
        this(name);
        this.block = block;
    }

    public void registerItem()
    {
        _isItemRegistered = true;
    }
    public void registerBlock()
    {
        _isItemRegistered = true;
    }
    public void initModel()
    {
        if (isModelInitialized())
            return;

        if (item != null && item instanceof BaseItem)
            ((BaseItem)item).initModel();

        if (block != null)
            ((BaseBlock)block).initModel();

        _isModelInitialized = true;
    }
}
