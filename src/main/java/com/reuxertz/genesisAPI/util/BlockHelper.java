package com.reuxertz.genesisAPI.util;

import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class BlockHelper {

    public static BlockPos getRandomBlock(BlockPos pos, double xySpread, double zSpread, Random random)
    {
        xySpread += .5;
        zSpread += .5;

        int randomX = (int)(xySpread * ((random.nextDouble() - .5) * 2.0));
        int randomY = (int)(xySpread * ((random.nextDouble() - .5) * 2.0));
        int randomZ = (int)(zSpread * ((random.nextDouble() - .5) * 2.0));

        BlockPos randBlockPos = pos.add(randomX, randomY, randomZ);

        return randBlockPos;
    }
}
