package com.reuxertz.genesis.api.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BaseBlockMetal extends BaseBlock {

    public BaseBlockMetal(String name, CreativeTabs tab) {
        super(name, Material.IRON, tab);
    }

    public BaseBlockMetal(String name) {
        this(name, CreativeTabs.MISC);
    }
}