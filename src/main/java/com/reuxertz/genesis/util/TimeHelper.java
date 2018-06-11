package com.reuxertz.genesis.util;

public class TimeHelper {

    public static double ConvertYearsToTicks(double years)
    {
        return years * 24000.0 * 7.0;
    }
}
