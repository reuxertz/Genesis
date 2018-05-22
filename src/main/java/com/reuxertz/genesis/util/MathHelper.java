package com.reuxertz.genesis.util;

public class MathHelper {

    public static double Log(double value, double base)
    {
        return Math.log(value) / Math.log(base);
    }
    public static double Log(double value)
    {
        return Math.log(value) / Math.log(10);
    }
    public static double Ln(double value)
    {
        return Math.log(value) / Math.log(Math.E);
    }
}
