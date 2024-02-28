package ian.parkingfeecalculator.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public class Calendar {


    boolean isHoliday(LocalDateTime today) {
        return List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(today.getDayOfWeek());
    }
}