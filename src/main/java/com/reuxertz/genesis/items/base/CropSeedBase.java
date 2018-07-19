package com.reuxertz.genesis.items.base;

import com.reuxertz.genesis.block.base.BlockCropBase;
import com.reuxertz.genesisAPI.organics.Genome;
import com.reuxertz.genesisAPI.organics.GenomeHelper;
import com.reuxertz.genesis.tileentities.TileEntityCropBase;
import com.reuxertz.genesisAPI.util.PlantHelper;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CropSeedBase extends ItemSeeds implements IItemBase {

    protected BlockCropBase blockCrop;

    public void setBlockCrop(BlockCropBase blockCrop) { this.blockCrop = blockCrop; }
    public BlockCrops getBlockCrop() { return blockCrop; }

    public CropSeedBase(String name, BlockCropBase blockCrop) {
        this(name, blockCrop, CreativeTabs.MISC);
    }
    public CropSeedBase(String name, BlockCropBase blockCrop, CreativeTabs tab) {
        super(blockCrop, Blocks.FARMLAND);
        setCreativeTab(tab);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        IBlockState state = worldIn.getBlockState(pos);

        boolean cpe = player.canPlayerEdit(pos.offset(facing), facing, itemstack);
        boolean csp = PlantHelper.canBlockSustainGenesisGrowable(state.getBlock(), worldIn.getBlockState(pos.up()).getBlock());

        if (facing == EnumFacing.UP && cpe && csp)
        {
            worldIn.setBlockState(pos.up(), this.blockCrop.getDefaultState());
            TileEntityCropBase tileEntityCropBase = (TileEntityCropBase)worldIn.getTileEntity(pos.up());

            GenomeHelper.validateNBT(itemstack);

            Genome genome = Genome.readFromNBT(itemstack.getTagCompound());
            tileEntityCropBase.getOrganism().getGenome().setSequence(genome.getSequence1(), genome.getSequence2());
            GenomeHelper.validateGenome(
                    "onion", //itemstack.getTagCompound().getString("name"),
                    "", //itemstack.getTagCompound().getString("breed"),
                    tileEntityCropBase.getOrganism().getGenome());

            if (player instanceof EntityPlayerMP)
                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos.up(), itemstack);

            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {

        ModelResourceLocation normal = new ModelResourceLocation(getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(this, 0, normal);
    }

    public void validateGenetics()
    {

    }
}