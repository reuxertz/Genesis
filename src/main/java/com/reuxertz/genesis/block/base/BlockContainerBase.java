package com.reuxertz.genesis.block.base;

import com.reuxertz.genesis.tileentities.TileEntityContainerBase;
import com.reuxertz.genesis.mod.Genesis;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockContainerBase extends BlockBase implements ITileEntityProvider {

    protected ResourceLocation guiTexture = new ResourceLocation(Genesis.MODID, "textures/gui/<replace>.png");

    public BlockContainerBase(String name)
    {
        super(name, Material.WOOD);
        guiTexture = new ResourceLocation(guiTexture.getResourceDomain(), guiTexture.getResourcePath().replace("<replace>", name));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote) {
            playerIn.openGui(Genesis.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
//        TileEntity tileEntity = worldIn.getTileEntity(pos);
//        if(tileEntity instanceof AirCompressorBlockEntity) {
//            IItemHandler item = ((AirCompressorBlockEntity)tileEntity).getInventory();
//            for (int i = 0; i < item.getSlots(); i++) {
//                ItemStack stack = item.getStackInSlot(i);
//                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
//            }
//        }
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        TileEntityContainerBase newTileEntity = new TileEntityContainerBase(guiTexture);
        return newTileEntity;
    }



}
