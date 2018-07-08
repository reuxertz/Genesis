package com.reuxertz.gaia.blocks;

import com.reuxertz.genesis.api.blocks.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockBaseOre extends BlockBase {

    public BlockBaseOre(String name, CreativeTabs tab) {
        super(name, Material.ROCK, tab);
    }

    public BlockBaseOre(String name) {
        this(name, CreativeTabs.MISC);
    }
}