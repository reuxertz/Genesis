package com.reuxertz.gaia.blocks;

import com.reuxertz.gaia.tileentities.TileEntityGroundItem;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BaseBlockGroundItem extends BlockContainer {
    public BaseBlockGroundItem() {
        super(Material.WOOD);
        this.setUnlocalizedName("block_item");
        this.setHardness(0.0F);
        this.setResistance(0.0F);
        //this.setsetBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.1F, 0.9F);
    }

    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te) {
        //player.triggerAchievement(StatList.mineBlockStatArray[getIdFromBlock(this)]);
        player.addExhaustion(0.025F);

        java.util.ArrayList<ItemStack> items = new java.util.ArrayList<ItemStack>();

        TileEntityGroundItem tile = (TileEntityGroundItem) te;

        //items.add(tile.getStack());

        for (ItemStack stack : items) {
            worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), stack.copy()));
        }
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityGroundItem();
    }
}


