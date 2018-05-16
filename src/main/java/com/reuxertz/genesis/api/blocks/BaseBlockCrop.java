package com.reuxertz.genesis.api.blocks;

import com.reuxertz.genesis.organisms.Genome;
import com.reuxertz.genesis.organisms.Metabolism;
import com.reuxertz.genesis.organisms.Organism;
import com.reuxertz.genesis.registry.SpeciesRegistry;
import com.reuxertz.genesis.tileentity.TileEntityBaseCrop;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

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
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        //super.updateTick(worldIn, pos, state, rand);
        this.checkAndDropBlock(worldIn, pos, state);

        TileEntityBaseCrop te = (TileEntityBaseCrop)worldIn.getTileEntity(pos);
        te.tick(worldIn);

        if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
        {
            int i = this.getAge(state);

            if (i < this.getMaxAge())
            {
                  float f = getGrowthChance(this, worldIn, pos);
//
//                if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int)(25.0F / f) + 1) == 0))
//                {
//                    worldIn.setBlockState(pos, this.withAge(i + 1), 2);
//                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
//                }
            }
        }
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

        Organism o = new Organism(shortName, g, new Metabolism());

        return new TileEntityBaseCrop(o);
    }
}