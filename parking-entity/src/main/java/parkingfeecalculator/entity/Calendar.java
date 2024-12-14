package parkingfeecalculator.entity;

import java.time.LocalDate;

public interface Calendar {
    boolean isHoliday(LocalDate today);
}
