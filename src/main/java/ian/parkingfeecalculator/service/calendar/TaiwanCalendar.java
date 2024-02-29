package ian.parkingfeecalculator.service.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaiwanCalendar implements Calendar {
    private Set<LocalDate> nationalHolidays = new HashSet<>();

    public TaiwanCalendar() {
        nationalHolidays.add(LocalDate.parse("2024-01-01"));
        nationalHolidays.add(LocalDate.parse("2024-02-28"));
    }

    public boolean isHoliday(LocalDate today) {
        boolean isNationalHolidays = nationalHolidays.contains(today);
        boolean isWeekend = List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(today.getDayOfWeek());
        return isWeekend || isNationalHolidays;
    }
}