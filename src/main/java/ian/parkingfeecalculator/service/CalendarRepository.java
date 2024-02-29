package ian.parkingfeecalculator.service;

import lombok.Data;

@Data
public class CalendarRepository {
    private TaiwanCalendar calendar = new TaiwanCalendar();
}
