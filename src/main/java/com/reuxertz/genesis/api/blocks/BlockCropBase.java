package com.reuxertz.genesis.api.blocks;

import com.reuxertz.genesis.organics.Genome;
import com.reuxertz.genesis.organics.Organism;
import com.reuxertz.genesis.api.tileentities.TileEntityCropBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCropBase extends BlockGrowableBase implements ITileEntityProvider
{
    protected Item crop;

    @Override
    protected Item getCrop() {
        return crop;
    }


    public BlockCropBase(String name) {
        this(name, CreativeTabs.MISC);
    }
    public BlockCropBase(String name, CreativeTabs tab) {
        super(name, tab);

    }

    public BlockCropBase setSeed(Item seed)
    {
        this.seed = seed;
        return this;
    }
    public BlockCropBase setCrop(Item crop)
    {
        this.crop = crop;
        return this;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        this.checkAndDropBlock(worldIn, pos, state);
    }


    @Override
    public TileEntity createNewTileEntity(World world, int meta) {

        String shortName = name.substring(name.lastIndexOf("_") + 1, name.length());
        Genome g = new Genome("", "");//SpeciesRegistry.getSpeciesGenome(shortName);

        Organism o = new Organism(shortName, g, 0, 10);
        TileEntityCropBase newTileEntity = new TileEntityCropBase(o, shortName);

        o.addEnergy(o.getMass());
        o.setOrganismContainer(newTileEntity);

        return newTileEntity;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        EnumHand ah = playerIn.getActiveHand();
        if (ah == hand)
        {
            ItemStack is = playerIn.getHeldItem(hand);
            TileEntityCropBase tileEntityCropBase = (TileEntityCropBase)worldIn.getTileEntity(pos);

            if (is.isEmpty() || is == ItemStack.EMPTY)
            {
                int newbornCount = tileEntityCropBase.getOrganism().removeNewborn();
                if (newbornCount == 0)
                    return false;

                Item item = tileEntityCropBase.getRegistryObject().item;
                ItemStack newbornItemStack = new ItemStack(item, newbornCount);
                NBTTagCompound newNBTTagCompound = new NBTTagCompound();
                newNBTTagCompound = tileEntityCropBase.getOrganism().getGenome().writeToNBT(newNBTTagCompound);
                newbornItemStack.setTagCompound(newNBTTagCompound);
                playerIn.setHeldItem(hand, newbornItemStack);

                tileEntityCropBase.refreshState();
                return true;
            }

            if (is.getItem() == tileEntityCropBase.getRegistryObject().item)
            {
                int newbornCount = tileEntityCropBase.getOrganism().removeNewborn();
                if (newbornCount == 0)
                    return false;

                if (is.getCount() + newbornCount <= is.getMaxStackSize())
                {
                    is.grow(newbornCount);
                    playerIn.setHeldItem(hand, is);
                }

                tileEntityCropBase.refreshState();
                return true;
            }
        }


        return false;
    }
}