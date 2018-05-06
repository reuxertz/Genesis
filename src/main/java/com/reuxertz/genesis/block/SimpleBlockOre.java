package com.reuxertz.genesis.block;

import com.reuxertz.genesis.api.block.BaseBlock;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class SimpleBlockOre extends BaseBlock {

    public SimpleBlockOre(String name, Material material) {
        super(name, material, CreativeTabs.MISC);
    }

    public SimpleBlockOre(String name) {
        this(name, Material.ROCK);
    }

}