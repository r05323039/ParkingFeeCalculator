package ian.parkingfeecalculator.service;

import ian.parkingfeecalculator.service.calendar.CalendarRepository;
import ian.parkingfeecalculator.service.calendar.TaiwanCalendarRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;

public class ParkingFeeCalculator {

    private final Duration _15_MINUTES = Duration.ofMinutes(15);
    private final Duration _30_MINUTES = Duration.ofMinutes(30);
    private final CalendarRepository calendarRepository;

    public ParkingFeeCalculator(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    public long getFee(ParkingInterval parkingInterval) {
        Duration duration = parkingInterval.getTotalDuration();
        if (isFreeInterval(duration)) {
            return 0;
        }

        List<DailySession> dailySessions = parkingInterval.getDailySessions();

        long totalFee = dailySessions.stream()
                .mapToLong(this::getRegularFeeDuringOnyDay).sum();
        return totalFee;
    }

    private long getRegularFeeDuringOnyDay(DailySession dailySession) {
        Duration duration = dailySession.getTodayDuration();

        long intervals = BigDecimal.valueOf(duration.toNanos())
                .divide(BigDecimal.valueOf(_30_MINUTES.toNanos())
                        , 0, RoundingMode.UP).longValue();

        boolean isHoliday = calendarRepository.getCalendar().isHoliday(dailySession.getToday());
        long feePerHalfHour = isHoliday ? 50 : 30;
        long todayFee = intervals * feePerHalfHour;

        return isHoliday ? Math.min(todayFee, 2400) : Math.min(todayFee, 150);
    }

    private boolean isFreeInterval(Duration duration) {
        return duration.compareTo(_15_MINUTES) <= 0;
    }
}
