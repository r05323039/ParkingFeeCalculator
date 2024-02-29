package ian.parkingfeecalculator.service.calendar;

import java.time.LocalDate;

public interface Calendar {
    boolean isHoliday(LocalDate today);
}
