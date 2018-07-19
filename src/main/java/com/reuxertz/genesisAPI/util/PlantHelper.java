package com.reuxertz.genesisAPI.util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlantHelper
{
    public static boolean canBlockSustainGenesisGrowable(Block blockBottom, Block blockTop)
    {
        return (blockBottom == Blocks.FARMLAND || blockBottom == Blocks.DIRT || blockBottom == Blocks.GRASS)
                && (blockTop == null || blockTop == Blocks.AIR);
    }
    public static boolean canBlockSustainGenesisPlant(Block blockBottom, Block blockTop)
    {
        return (blockBottom == Blocks.DIRT || blockBottom == Blocks.GRASS)
                && (blockTop == null || blockTop == Blocks.AIR);
    }

    public static boolean spreadPlant(World world, BlockPos pos)
    {
        BlockPos randBlockPos = BlockHelper.getRandomBlock(pos.down(), 4, 2, RandomHelper.random);

        Block block = world.getBlockState(randBlockPos).getBlock();
        Block topBlock = world.getBlockState(randBlockPos.up()).getBlock();
        Block parentBlock = world.getBlockState(pos).getBlock();

        if (PlantHelper.canBlockSustainGenesisPlant(block, topBlock)) {

            world.setBlockState(randBlockPos.up(), parentBlock.getDefaultState());
            return true;
        }

        return false;
    }



}