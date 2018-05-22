package com.reuxertz.genesis.util;

public class EnergyHelper {

    public static final double SunlightEnergyPerGramDay = 1;
    public static final double SunlightEnergyPerGramTick = convertDayToTick(SunlightEnergyPerGramDay);
    public static final double EnergyToGramRatio = 1;

    public static final double EnergyCostToGramExponent = .75;

    public static double convertDayToTick(double value)
    {
        return value / (20.0 * 60.0 * 20.0);
    }

    public static double getEnergyCostPerGramDay(double mass)
    {
        return Math.pow(mass, EnergyCostToGramExponent) / mass;
    }
    public static double getEnergyCostPerGramTick(double mass)
    {
        return convertDayToTick(getEnergyCostPerGramDay(mass));
    }
}
