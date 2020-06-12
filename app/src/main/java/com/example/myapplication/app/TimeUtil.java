package com.example.myapplication.app;

public class TimeUtil {
    private static long startTime;

    public static long getStartTime(){
        startTime = System.currentTimeMillis();
        return startTime;
    }

    public static long getEndTime(){
        return System.currentTimeMillis() - startTime;
    }
}
