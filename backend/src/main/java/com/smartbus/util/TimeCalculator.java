package com.smartbus.util;

import java.time.LocalTime;

public class TimeCalculator {

    public static LocalTime addMinutes(
            LocalTime time,
            int minutes) {

        return time.plusMinutes(minutes);
    }

    public static String formatTime(
            LocalTime time) {

        return time.toString();
    }
}