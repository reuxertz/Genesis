package com.reuxertz.gaia.blocks;

import com.reuxertz.genesis.api.blocks.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockOreBase extends BlockBase {

    public BlockOreBase(String name, CreativeTabs tab) {
        super(name, Material.ROCK, tab);
    }

    public BlockOreBase(String name) {
        this(name, CreativeTabs.MISC);
    }
}