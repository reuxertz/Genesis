package com.reuxertz.genesis.api.blocks;

import com.reuxertz.genesis.organisms.Genome;
import com.reuxertz.genesis.registry.SpeciesRegistry;
import com.reuxertz.genesis.tileentity.TileEntityBaseCrop;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseBlockCrop extends BlockCrops implements IBaseBlock
{
    protected String name;
    protected Item seed;
    protected Item crop;

    public BaseBlockCrop(String name) {
        this(name, CreativeTabs.MISC);
    }
    public BaseBlockCrop(String name, CreativeTabs tab) {
        super();
        this.name = name;
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

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {

        String shortName = name.substring(name.lastIndexOf("_") + 1, name.length());
        Genome g = SpeciesRegistry.getSpeciesGenome(shortName);

        return new TileEntityBaseCrop(g);
    }
}