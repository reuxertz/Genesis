package com.reuxertz.genesisAPI.util;

public class MathHelper {

    public static double Log(double value, double base) {
        return Math.log(value) / Math.log(base);
    }

    public static double Log(double value) {
        return Math.log(value) / Math.log(10);
    }

    public static double Ln(double value) {
        return Math.log(value) / Math.log(Math.E);
    }

    public static double Sigmoid(double value, double slope) {
        double result = 1.0 / (1.0 + Math.pow(Math.E, -1.0 * value * slope));

        return result;
    }
    public static double PseudoLinear(double value, double base) {

        double expFactor = MathHelper.Log(1 + Math.pow(base, value), base);
        double result = expFactor * (1.0 / MathHelper.Log(2, base));
        return result;
    }
}
