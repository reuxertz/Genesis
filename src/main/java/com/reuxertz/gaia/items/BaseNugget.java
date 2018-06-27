package com.reuxertz.gaia.items;

import com.reuxertz.genesis.api.items.BaseItem;

public class BaseNugget extends BaseItem
{
    public BaseNugget(String name)
    {
        super(name);
        this.setMaxStackSize(64);
    }
}
