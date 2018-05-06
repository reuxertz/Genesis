package com.reuxertz.genesis.api.block;

import com.reuxertz.genesis.Genesis;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BaseCrop extends BlockCrops implements IGrowable, IBaseBlock
{
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
    public static final PropertyInteger HEIGHT = PropertyInteger.create("heighResStart", 0, 1);

    protected Item seed, crop;
    protected boolean continualYield = false;

    public int getCropYield(Random r)
    {
        return r.nextInt(3);
    }

    public BaseCrop setContinualYield(boolean b)
    {
        this.continualYield = b;
        return this;
    }
    public boolean hasContinualYield()
    {
        return this.continualYield;
    }

    public BaseCrop setSeedCrop(Item seed, Item crop)
    {
        this.seed = seed;
        this.crop = crop;

        return this;
    }
    public BaseCrop setSeedCrop(Item seedCrop)
    {
        this.setSeedCrop(seedCrop, seedCrop);

        return this;
    }
    public BaseCrop setRecipe(Item seedCrop, Item seed)
    {
        //CraftingManager.get().addShapelessRecipe(new ItemStack(seed), new ItemStack(seedCrop));
        return this;
    }
    public BaseCrop(String name, Item seed, Item crop)
    {
        this(name);
        this.setSeedCrop(seed, crop);
    }
    public BaseCrop(String name)
    {
        setRegistryName(name);
        setUnlocalizedName(Genesis.MODID + "." + name);

        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
        this.setTickRandomly(true);
        float f = 0.5F;
        //this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.setCreativeTab(null);
        this.setHardness(0.0F);
        //this.setStepSound(soundTypeGrass);
        this.disableStats();
    }

    public void initModel() {

        ModelResourceLocation normal = new ModelResourceLocation(getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, normal);
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
//        NBTTagCompound nbt = TileEntityEcoBedrock.getPosTag(world, pos);
//        if (nbt != null)
//        {
//            //for (ItemStack stack : getTileBreakDrops(te))
//            ItemStack ns = new ItemStack(Item.getItemFromBlock(state.getBlock()));
//            ns.setTagCompound(nbt);
//            TileEntityEcoBedrock.removePosTag(world, pos);
//            Entity e = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), ns);
//            world.spawnEntityInWorld(e);
//        }
//        else
//            super.breakBlock(world, pos, state);
//
//        world.removeTileEntity(pos);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
//        if (stack.hasTagCompound())
//            TileEntityEcoBedrock.registerPosTag(worldIn, pos, state.getBlock(), stack.getTagCompound());

        return;
    }

    public boolean cropActivated(World w, BlockPos pos, IBlockState state)
    {
        if (this.continualYield && ((Integer)state.getValue(AGE)).intValue() == 7)
        {
            if (!w.isRemote)
            {
//                NBTTagCompound nnbt = TileEntityEcoBedrock.getPosTag(w, pos);
//                if (nnbt == null)
//                    nnbt = new NBTTagCompound();
//
//                ItemStack ns = new ItemStack(this.crop);
//                ns.setTagCompound(nnbt);

                w.spawnEntity(new EntityItem(w, pos.getX(), pos.getY() + .5, pos.getZ()));
            }

            w.setBlockState(pos, state
                    .withProperty(AGE, ((Integer) state.getValue(AGE)).intValue() - 3));
            return true;
        }
        return false;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        this.cropActivated(worldIn, pos, state);

        return super.onBlockActivated(worldIn, pos, state, playerIn, hand,side, hitX, hitY, hitZ);
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state)
    {
        return ColorizerFoliage.getFoliageColorBasic();
    }
    @Override
    public boolean canSustainBush(IBlockState state)
    {
        Block ground = state.getBlock();
        boolean b = ground == Blocks.FARMLAND || ground == Blocks.GRASS || ground == Blocks.DIRT;
        return b;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        //super.updateTick(worldIn, pos, state, rand);
        this.checkAndDropBlock(worldIn, pos, state);

        IBlockState below = worldIn.getBlockState(pos.down());
        Block belowBlock = below.getBlock();

        if (belowBlock != Blocks.FARMLAND && rand.nextDouble() > .5)
            return;

        if (belowBlock == Blocks.GRASS && rand.nextDouble() > .5)
            return;

        /*for (int x = -4; x <= 4; x++)
            for (int z = -4; z <= 4; z++)
                for (int y = -1; y <= 1; y++)
                {
                    IBlockState bs = worldIn.getBlockState(pos.add(x, y, z).down());
                    Block b = bs.getBlock();

                    boolean hasFresh = b.getUnlocalizedName().toLowerCase().contains("freshwater") || b.getMaterial() == EcoTerra.materialFreshWater;

                    if (!hasFresh && rand.nextDouble() > .5)
                        return;
                }*/

        if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
        {
            int i = ((Integer)state.getValue(AGE)).intValue();

            if (i > 7)
            {
                i = 7;
                worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(i)), 2);
            }
            if (i < 7)
            {
                float f = getGrowthChance(this, worldIn, pos);

                /*
                Iterator iterator = BlockPos.getAllInBoxMutable(pos.add(-4, -1, -4), pos.add(4, 1, 4)).iterator();
                BlockPos.MutableBlockPos mutableblockpos;

                boolean r = false, brk = false;
                {
                    do
                    {
                        if (!iterator.hasNext())
                        {
                            r = false;
                            brk  = true;
                            break;
                        }

                        mutableblockpos = (BlockPos.MutableBlockPos) iterator.next();

                        IBlockState state1 = worldIn.getBlockState(mutableblockpos);
                        Block b1 = state1.getBlock();
                        Material m = b1.getMaterial();

                        if (m == Material.water)
                        {
                            break;
                        }
                    }
                    while (worldIn.getBlockState(mutableblockpos).getBlock().getMaterial() != Material.water);

                    if (!brk)
                        r = true;
                }

                if (!r)
                {
                    int a  = 7;
                }*/

                if (rand.nextInt((int)(25.0F / f) + 1) == 0)
                {
                    i += rand.nextInt(2) + 1;

                    if (i > 7)
                    {
                        i = 7;
                    }

                    worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(i)), 2);
                }
            }
        }
    }

    public void grow(World worldIn, BlockPos pos, IBlockState state)
    {
        int i = ((Integer)state.getValue(AGE)).intValue() + MathHelper.getInt(worldIn.rand, 2, 5);

        if (i > 7)
        {
            i = 7;
        }

        worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(i)), 2);
    }

    public static float getGrowthChance(Block blockIn, World worldIn, BlockPos pos)
    {
        float f = 1.0F;
        BlockPos blockpos1 = pos.down();

        for (int i = -1; i <= 1; ++i)
        {
            for (int j = -1; j <= 1; ++j)
            {
                float f1 = 0.0F;
                IBlockState iblockstate = worldIn.getBlockState(blockpos1.add(i, 0, j));

                if (iblockstate.getBlock().canSustainPlant(iblockstate, worldIn, blockpos1.add(i, 0, j), net.minecraft.util.EnumFacing.UP, (net.minecraftforge.common.IPlantable)blockIn))
                {
                    f1 = 1.0F;

                    if (iblockstate.getBlock().isFertile(worldIn, blockpos1.add(i, 0, j)))
                    {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0)
                {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos2 = pos.north();
        BlockPos blockpos3 = pos.south();
        BlockPos blockpos4 = pos.west();
        BlockPos blockpos5 = pos.east();
        boolean flag = blockIn == worldIn.getBlockState(blockpos4).getBlock() || blockIn == worldIn.getBlockState(blockpos5).getBlock();
        boolean flag1 = blockIn == worldIn.getBlockState(blockpos2).getBlock() || blockIn == worldIn.getBlockState(blockpos3).getBlock();

        if (flag && flag1)
        {
            f /= 2.0F;
        }
        else
        {
            boolean flag2 = blockIn == worldIn.getBlockState(blockpos4.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos5.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos5.south()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock();

            if (flag2)
            {
                f /= 2.0F;
            }
        }

        return f;
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canSustainBush(worldIn.getBlockState(pos.down())))
            return false;

        return (worldIn.getLight(pos) >= 8 || worldIn.canSeeSky(pos)) &&
                worldIn.getBlockState(pos.down()).getBlock().canSustainPlant(worldIn.getBlockState(pos.down()), worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
    }

    public Item getSeed(){
        return seed;
    }

    public Item getCrop(){
        return crop;
    }

    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, 0);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ((Integer)state.getValue(AGE)).intValue() == 7 ? this.getCrop() : this.getSeed();
    }

    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return ((Integer)state.getValue(AGE)).intValue() < 7;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        this.grow(worldIn, pos, state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(AGE)).intValue();
    }

    @Override
    public BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, AGE);
    }
}
