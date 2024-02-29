package ian.parkingfeecalculator.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Calendar {


    boolean isHoliday(LocalDate today) {

        Set<LocalDate> nationalHolidays = new HashSet<>();
        return List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(today.getDayOfWeek());
    }
}