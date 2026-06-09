package com.smartbus.util;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduleGenerator {

    public static List<LocalTime> generateSchedule(

            LocalTime startTime,

            LocalTime endTime,

            int intervalMinutes) {

        List<LocalTime> schedules =
                new ArrayList<>();

        LocalTime current =
                startTime;

        while (!current.isAfter(endTime)) {

            schedules.add(current);

            current =
                    current.plusMinutes(
                            intervalMinutes);
        }

        return schedules;
    }
}