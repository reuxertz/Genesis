package com.reuxertz.gaia.items;

import com.reuxertz.genesis.api.items.BaseItem;

public class BaseIngot extends BaseItem
{
    public BaseIngot(String name)
    {
        super(name);
        this.setMaxStackSize(64);
    }
}
