package com.reuxertz.genesis.api.items;

public class BaseIngot extends BaseItem
{
    public BaseIngot(String name)
    {
        super(name);
        this.setMaxStackSize(64);
    }
}
