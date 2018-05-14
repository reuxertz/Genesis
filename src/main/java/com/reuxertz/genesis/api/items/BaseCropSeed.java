package com.reuxertz.genesis.api.items;

import com.reuxertz.genesis.api.blocks.BaseBlockCrop;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseCropSeed extends ItemSeeds implements IBaseItem {

    public BaseCropSeed(String name, BaseBlockCrop blockCrop) {
        this(name, blockCrop, CreativeTabs.MISC);
    }
    public BaseCropSeed(String name, BaseBlockCrop blockCrop, CreativeTabs tab) {
        super(blockCrop, Blocks.FARMLAND);
        setCreativeTab(tab);
    }

//    /**
//     * Called when a Block is right-clicked with this Item
//     */
//    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
//    {
//        ItemStack itemstack = player.getHeldItem(hand);
//        net.minecraft.blocks.state.IBlockState state = worldIn.getBlockState(pos);
//        if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && state.getBlock().canSustainPlant(state, worldIn, pos, EnumFacing.UP, this) && worldIn.isAirBlock(pos.up()))
//        {
//            worldIn.setBlockState(pos.up(), this.crops.getDefaultState());
//
//            if (player instanceof EntityPlayerMP)
//            {
//                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos.up(), itemstack);
//            }
//
//            itemstack.shrink(1);
//            return EnumActionResult.SUCCESS;
//        }
//        else
//        {
//            return EnumActionResult.FAIL;
//        }
//    }
//
//    @Override
//    public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
//    {
//        return this.crops == net.minecraft.init.Blocks.NETHER_WART ? net.minecraftforge.common.EnumPlantType.Nether : net.minecraftforge.common.EnumPlantType.Crop;
//    }
//
//    @Override
//    public net.minecraft.blocks.state.IBlockState getPlant(net.minecraft.world.IBlockAccess world, BlockPos pos)
//    {
//        return this.crops.getDefaultState();
//    }

    @SideOnly(Side.CLIENT)
    public void initModel() {

        ModelResourceLocation normal = new ModelResourceLocation(getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(this, 0, normal);
    }
}
