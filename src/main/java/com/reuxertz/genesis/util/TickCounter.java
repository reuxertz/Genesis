package com.reuxertz.genesis.util;

import com.reuxertz.genesis.organics.Genome;
import com.reuxertz.genesis.organics.IOrganismContainer;
import com.reuxertz.genesis.organics.Organism;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Random;

public class TickCounter {

    private long lastTick = -1;
    private long lastTickDif = -1;

    private Random random;
    private int randomSpread = 3;
    private int wait = 50;
    private boolean randomize = true;

    public long getLastTickDif()
    {
        return lastTickDif;
    }
    public int getWaitTicks() { return wait; }

    public TickCounter(Random random, boolean randomize)
    {
        this.random = random;
        this.randomize = randomize;
    }

    public int getTicks(long currentTick)
    {
        if (lastTick < 0)
            lastTick = currentTick;

        long dif = currentTick - lastTick;
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

            dif = currentTick - tLastTick;
        }

        if (tickCount == 0)
            return 0;

        lastTick = tLastTick;
        return tickCount;
    }
}
