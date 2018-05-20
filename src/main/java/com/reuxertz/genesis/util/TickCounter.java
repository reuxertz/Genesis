package com.reuxertz.genesis.util;

import java.util.Random;

public class TickCounter {

    private long lastTick = -1;
    private long lastTickDif = -1;

    private Random random;
    private int randomSpread = 10;
    private int wait = 50;
    private boolean randomize = true;

    public long getLastTickDif()
    {
        return lastTickDif;
    }

    public TickCounter(Random random, boolean randomize)
    {
        this.random = random;
        this.randomize = randomize;
    }

    public boolean isTicked(long tick)
    {
        if (lastTick < 0)
            lastTick = tick;

        long dif = tick - lastTick;

        if (dif >= wait)
        {
            if (randomize)
                lastTick = tick + (random.nextInt(randomSpread * 2) - randomSpread);
            else
                lastTick = tick;

            lastTickDif = dif;

            return true;
        }

        return false;
    }
}
