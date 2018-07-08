package com.reuxertz.gaia.items;

import com.reuxertz.genesis.api.items.ItemBase;

public class IngotBase extends ItemBase
{
    public IngotBase(String name)
    {
        super(name);
        this.setMaxStackSize(64);
    }
}
