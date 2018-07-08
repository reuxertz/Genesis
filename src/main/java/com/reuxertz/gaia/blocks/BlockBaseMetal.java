package com.reuxertz.gaia.blocks;

import com.reuxertz.genesis.api.blocks.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockBaseMetal extends BlockBase {

    public BlockBaseMetal(String name, CreativeTabs tab) {
        super(name, Material.IRON, tab);
    }

    public BlockBaseMetal(String name) {
        this(name, CreativeTabs.MISC);
    }
}