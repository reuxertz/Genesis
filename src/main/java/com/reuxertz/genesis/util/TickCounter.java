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

    public int getTicks(long tick)
    {
        if (lastTick < 0)
            lastTick = tick;

        long dif = tick - lastTick;
        lastTickDif = dif;

        int tickCount = 0;
        long tLastTick = lastTick;
        while (dif >= wait) {
            if (dif >= wait) {
                if (randomize)
                    tLastTick = tLastTick + wait + (random.nextInt(randomSpread * 2) - randomSpread);
                else
                    tLastTick = tLastTick + wait;

                tickCount++;
            }

            dif = tick - tLastTick;
        }

        if (tickCount == 0)
            return 0;

        lastTick = tLastTick;
        return tickCount;
    }
}
