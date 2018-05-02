package com.reuxertz.genesis.block;

import com.reuxertz.genesis.api.block.BaseBlock;
import com.reuxertz.genesis.api.item.BaseItem;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class SimpleBlock extends BaseBlock {

    public SimpleBlock(String name, Material material) {
        super(name, material, CreativeTabs.MISC);
    }

    public SimpleBlock(String name) {
        this(name, Material.ROCK);
    }

}