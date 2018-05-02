package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.api.block.BaseBlock;
import com.reuxertz.genesis.api.item.BaseItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class RegistryObject {

    public String name;
    public Block block;
    public Item item;

    protected boolean _isRegistered;

    public boolean isRegistered()
    {
        return _isRegistered;
    };

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

    public void Register()
    {
        _isRegistered = true;
    }

    public void initModel()
    {
        if (item != null && item instanceof BaseItem)
            ((BaseItem)item).initModel();
    }
}
