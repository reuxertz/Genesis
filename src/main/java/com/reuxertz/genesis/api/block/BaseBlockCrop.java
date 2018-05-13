package com.reuxertz.genesis.api.block;

import com.reuxertz.genesis.api.items.BaseCropSeed;
import com.reuxertz.genesis.api.items.BaseItem;
import com.reuxertz.genesis.tileentity.TileEntityBaseCrop;
import jline.internal.Nullable;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseBlockCrop extends BlockCrops implements IBaseBlock, ITileEntityProvider {

    protected Item seed;
    protected Item crop;

    public BaseBlockCrop(String name) {
        this(name, CreativeTabs.MISC);
    }
    public BaseBlockCrop(String name, CreativeTabs tab) {
        super();
        setCreativeTab(tab);
    }

    public BaseBlockCrop setSeed(Item seed)
    {
        this.seed = seed;
        return this;
    }
    public BaseBlockCrop setCrop(Item crop)
    {
        this.crop = crop;
        return this;
    }

    @Override
    protected Item getSeed() {
        return seed;
    }

    @Override
    protected Item getCrop() {
        return crop;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {

        ModelResourceLocation normal = new ModelResourceLocation(getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, normal);
    }

    @Nullable
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityBaseCrop();
    }
}