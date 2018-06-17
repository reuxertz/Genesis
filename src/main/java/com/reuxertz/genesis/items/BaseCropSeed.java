package com.reuxertz.genesis.items;

import com.reuxertz.genesis.api.items.IBaseItem;
import com.reuxertz.genesis.blocks.BaseBlockGrowable;
import com.reuxertz.genesis.organics.Genome;
import com.reuxertz.genesis.organics.GenomeHelper;
import com.reuxertz.genesis.tileentity.TileEntityBaseCrop;
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

public class BaseCropSeed extends ItemSeeds implements IBaseItem {

    protected BaseBlockGrowable blockCrop;

    public void setBlockCrop(BaseBlockGrowable blockCrop) { this.blockCrop = blockCrop; }
    public BlockCrops getBlockCrop() { return blockCrop; }

    public BaseCropSeed(String name, BaseBlockGrowable blockCrop) {
        this(name, blockCrop, CreativeTabs.MISC);
    }
    public BaseCropSeed(String name, BaseBlockGrowable blockCrop, CreativeTabs tab) {
        super(blockCrop, Blocks.FARMLAND);
        setCreativeTab(tab);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        IBlockState state = worldIn.getBlockState(pos);

        boolean cpe = player.canPlayerEdit(pos.offset(facing), facing, itemstack);
        boolean csp = this.blockCrop.canBlockSustainGenesisPlant(state);

        if (facing == EnumFacing.UP && cpe && csp &&
                worldIn.isAirBlock(pos.up()))
        {
            worldIn.setBlockState(pos.up(), this.blockCrop.getDefaultState());
            TileEntityBaseCrop tileEntityBaseCrop = (TileEntityBaseCrop)worldIn.getTileEntity(pos.up());

            GenomeHelper.validateNBT(itemstack);

            Genome genome = Genome.readFromNBT(itemstack.getTagCompound());
            tileEntityBaseCrop.getOrganism().getGenome().setSequence(genome.getSequence1(), genome.getSequence2());
            GenomeHelper.validateGenome(tileEntityBaseCrop.getRegistryObject().name, tileEntityBaseCrop.getOrganism().getGenome());

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
