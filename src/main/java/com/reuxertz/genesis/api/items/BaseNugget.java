package com.reuxertz.genesis.api.items;

public class BaseNugget extends BaseItem
{
    public BaseNugget(String name)
    {
        super(name);
        this.setMaxStackSize(64);
    }
}
