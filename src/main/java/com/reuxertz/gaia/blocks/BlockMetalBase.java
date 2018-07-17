package com.reuxertz.gaia.blocks;

import com.reuxertz.genesis.block.base.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockMetalBase extends BlockBase {

    public BlockMetalBase(String name, CreativeTabs tab) {
        super(name, Material.IRON, tab);
    }

    public BlockMetalBase(String name) {
        this(name, CreativeTabs.MISC);
    }
}