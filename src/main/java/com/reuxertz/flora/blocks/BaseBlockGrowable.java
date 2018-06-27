package com.reuxertz.flora.blocks;

import com.reuxertz.genesis.api.blocks.IBaseBlock;
import com.reuxertz.genesis.organics.Genome;
import com.reuxertz.genesis.organics.Organism;
import com.reuxertz.genesis.tileentities.TileEntityBaseCrop;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BaseBlockGrowable extends BlockCrops implements IBaseBlock, ITileEntityProvider
{
    protected String name;
    protected Item seed;
    protected Item crop;

    @Override
    protected Item getSeed() {
        return seed;
    }

    @Override
    protected Item getCrop() {
        return crop;
    }

    @Override
    public int getAge(IBlockState state)
    {
        return ((Integer)state.getValue(this.getAgeProperty())).intValue();
    }

    public static boolean canBlockSustainGenesisPlant(IBlockState state)
    {
        return state.getBlock() == Blocks.FARMLAND || state.getBlock() == Blocks.DIRT
                || state.getBlock() == Blocks.GRASS;
    }

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        return canBlockSustainGenesisPlant(state);
    }

    public BaseBlockGrowable(String name) {
        this(name, CreativeTabs.MISC);
    }
    public BaseBlockGrowable(String name, CreativeTabs tab) {
        super();
        this.name = name;
        setCreativeTab(tab);

    }

    public BaseBlockGrowable setSeed(Item seed)
    {
        this.seed = seed;
        return this;
    }
    public BaseBlockGrowable setCrop(Item crop)
    {
        this.crop = crop;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {

        ModelResourceLocation normal = new ModelResourceLocation(getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, normal);
    }
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        //super.updateTick(worldIn, pos, state, rand);
        this.checkAndDropBlock(worldIn, pos, state);

        TileEntityBaseCrop te = (TileEntityBaseCrop)worldIn.getTileEntity(pos);
        //te.getOrganism().tick(worldIn);
        //te.refreshState();

//        if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
//        if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && false)
//        {
//            int i = this.getAge(state);
//            worldIn.setBlockState(pos, this.withAge(i + 1));
//
//            if (i < this.getMaxAge())
//            {
//                  float f = getGrowthChance(this, worldIn, pos);
//
//                if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int)(25.0F / f) + 1) == 0))
//                {
//                    //worldIn.setBlockState(pos, this.withAge(i + 1));
//                    //net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
//                }
//            }
//        }
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());
        return super.canPlaceBlockAt(worldIn, pos) && soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {

        String shortName = name.substring(name.lastIndexOf("_") + 1, name.length());
        Genome g = new Genome("", "");//SpeciesRegistry.getSpeciesGenome(shortName);

        Organism o = new Organism(shortName, g, 0, 10);
        TileEntityBaseCrop newTileEntity = new TileEntityBaseCrop(o, shortName);

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
            TileEntityBaseCrop tileEntityBaseCrop = (TileEntityBaseCrop)worldIn.getTileEntity(pos);

            if (is.isEmpty() || is == ItemStack.EMPTY)
            {
                int newbornCount = tileEntityBaseCrop.getOrganism().removeNewborn();
                if (newbornCount == 0)
                    return false;

                Item item = tileEntityBaseCrop.getRegistryObject().item;
                ItemStack newbornItemStack = new ItemStack(item, newbornCount);
                NBTTagCompound newNBTTagCompound = new NBTTagCompound();
                newNBTTagCompound = tileEntityBaseCrop.getOrganism().getGenome().writeToNBT(newNBTTagCompound);
                newbornItemStack.setTagCompound(newNBTTagCompound);
                playerIn.setHeldItem(hand, newbornItemStack);

                tileEntityBaseCrop.refreshState();
                return true;
            }

            if (is.getItem() == tileEntityBaseCrop.getRegistryObject().item)
            {
                int newbornCount = tileEntityBaseCrop.getOrganism().removeNewborn();
                if (newbornCount == 0)
                    return false;

                if (is.getCount() + newbornCount <= is.getMaxStackSize())
                {
                    is.grow(newbornCount);
                    playerIn.setHeldItem(hand, is);

//                    int newbornCountInt = tileEntityBaseCrop.getOrganism().removeNewborn();
//                    GenomeHelper.addGenomeToItemStack(is, tileEntityBaseCrop.getOrganism().getGenome().clone());
//                    is.grow(newbornCountInt);
//                    playerIn.setHeldItem(hand, is);
                }

                tileEntityBaseCrop.refreshState();
                return true;
            }
        }


        return false;
    }

//    @Override
//    public TileEntity createTileEntity(World world, IBlockState state) {
//
//        String shortName = name.substring(name.lastIndexOf("_") + 1, name.length());
//        Genome g = SpeciesRegistry.getSpeciesGenome(shortName);
//
//        Organism o = new Organism(shortName, g, new Metabolism(), 10);
//        TileEntityBaseCrop newTileEntity = new TileEntityBaseCrop(o);
//
//        o.getMetabolism().addEnergy(o.getMass());
//        o.setOrganismContainer(newTileEntity);
//
//        return newTileEntity;
//    }
}