package ian.parkingfeecalculator.repository;


import ian.parkingfeecalculator.entity.Calendar;
import ian.parkingfeecalculator.entity.TaiwanCalendar;
import org.springframework.stereotype.Repository;

@Repository
public class TaiwanCalendarRepository implements CalendarRepository {
    private TaiwanCalendar calendar = new TaiwanCalendar();

    @Override
    public Calendar getCalendar() {
        return calendar;
    }
}
