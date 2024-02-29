package ian.parkingfeecalculator.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Calendar {


    boolean isHoliday(LocalDate today) {

        Set<LocalDate> nationalHolidays = new HashSet<>();
        nationalHolidays.add(LocalDate.parse("2024-01-01"));
        nationalHolidays.add(LocalDate.parse("2024-02-28"));

        boolean isNationalHolidays = nationalHolidays.contains(today);
        boolean isWeekend = List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(today.getDayOfWeek());
        return isWeekend || isNationalHolidays;
    }
}