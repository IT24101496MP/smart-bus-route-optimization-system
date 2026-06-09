package com.smartbus.util;

public class CrowdCalculator {

    public static double calculateCrowdPercentage(
            int currentPassengers,
            int capacity) {

        if (capacity == 0) {
            return 0;
        }

        return (currentPassengers * 100.0)
                / capacity;
    }
}