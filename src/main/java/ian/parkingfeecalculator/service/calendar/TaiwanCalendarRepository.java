package ian.parkingfeecalculator.service.calendar;


public class TaiwanCalendarRepository implements CalendarRepository {
    private TaiwanCalendar calendar = new TaiwanCalendar();

    @Override
    public Calendar getCalendar() {
        return calendar;
    }
}
