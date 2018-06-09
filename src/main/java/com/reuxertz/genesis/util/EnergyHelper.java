package com.reuxertz.genesis.util;

public class EnergyHelper {

    public static final double SunlightEnergyPerGramDay = 1000;
    public static final double SunlightEnergyPerGramTick = convertDayToTick(SunlightEnergyPerGramDay);

    public static final double EnergyContentPerGram = 1;
    public static final double EnergyLossToGramExponent = .75;
    public static final double EnergyStorageDensityPerGram = 1;

    public static double getEnergyStorageCapacity(double mass)
    {
        return mass * EnergyStorageDensityPerGram;
    }

    public static double convertDayToTick(double value)
    {
        return value / (20.0 * 60.0 * 20.0);
    }

    public static double getMass(double energy)
    {
        return energy / EnergyContentPerGram;
    }

    public static double getEnergy(double mass)
    {
        return mass * EnergyContentPerGram;
    }
    public static double getEnergyLossPerGramDay(double mass)
    {
        return Math.pow(getEnergy(mass), EnergyLossToGramExponent) / mass;
    }
    public static double getEnergyLossPerGramTick(double mass)
    {
        return convertDayToTick(getEnergyLossPerGramDay(mass));
    }
}
