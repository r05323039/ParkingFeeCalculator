package ian.parkingfeecalculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.Duration;
import java.util.List;

public class ParkingFeeCalculator {

    private final Duration _15_MINUTES = Duration.ofMinutes(15);
    private final Duration _30_MINUTES = Duration.ofMinutes(30);

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

        boolean isHoliday = List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(dailySession.getToday().getDayOfWeek());
        long feePerHalfHour = isHoliday ? 50 : 30;
        long todayFee = intervals * feePerHalfHour;

        return isHoliday ? Math.min(todayFee, 2400) : Math.min(todayFee, 150);
    }

    private boolean isFreeInterval(Duration duration) {
        return duration.compareTo(_15_MINUTES) <= 0;
    }
}
