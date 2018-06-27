package com.reuxertz.gaia.blocks;

import com.reuxertz.genesis.api.blocks.BaseBlock;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BaseBlockOre extends BaseBlock {

    public BaseBlockOre(String name, CreativeTabs tab) {
        super(name, Material.ROCK, tab);
    }

    public BaseBlockOre(String name) {
        this(name, CreativeTabs.MISC);
    }
}