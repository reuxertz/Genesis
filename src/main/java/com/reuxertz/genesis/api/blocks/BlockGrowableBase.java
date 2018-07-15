package com.reuxertz.genesis.api.blocks;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGrowableBase extends BlockCrops implements IBaseBlock {

    protected String name;
    protected Item seed;

    public BlockGrowableBase(String name) {
        this(name, CreativeTabs.MISC);
    }
    public BlockGrowableBase(String name, CreativeTabs tab) {
        super();
        this.name = name;
        setCreativeTab(tab);
    }

    @Override
    protected Item getSeed() {
        return seed;
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

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());
        return super.canPlaceBlockAt(worldIn, pos) && soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
    }


    @SideOnly(Side.CLIENT)
    public void initModel() {

        ModelResourceLocation normal = new ModelResourceLocation(getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, normal);
    }
}
