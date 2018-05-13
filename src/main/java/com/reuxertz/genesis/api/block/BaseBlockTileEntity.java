package com.reuxertz.genesis.api.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BaseBlockTileEntity extends BaseBlock {

    public BaseBlockTileEntity(String name, Material material, CreativeTabs tab) {
        super(name, material, tab);

    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return null;
    }
}
