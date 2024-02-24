package ian.parkingfeecalculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingFeeCalculator {

    private final Duration _15_MINUTES = Duration.ofMinutes(15);
    private final Duration _30_MINUTES = Duration.ofMinutes(30);

    public long getFee(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        long fee = 0;
        if (isFreeInterval(duration)) {
            return fee;
        }

        LocalDateTime todayStart = start.toLocalDate().atStartOfDay();
        while (todayStart.isBefore(end)) {
            LocalDateTime intervalStart = start.isAfter(todayStart) ? start : todayStart;

            LocalDateTime tomorrowStart = todayStart.plusDays(1);
            LocalDateTime intervalEnd = end.isBefore(tomorrowStart) ? end : tomorrowStart;

            fee += getRegularFeeDuringOnyDay(Duration.between(intervalStart, intervalEnd));

            todayStart = tomorrowStart;
        }
        return fee;
    }

    private long getRegularFeeDuringOnyDay(Duration duration) {
        long intervals = BigDecimal.valueOf(duration.toNanos())
                .divide(BigDecimal.valueOf(_30_MINUTES.toNanos())
                        , 0, RoundingMode.UP).longValue();
        long fee = intervals * 30;
        return Math.min(fee, 150);
    }

    private boolean isFreeInterval(Duration duration) {
        return duration.compareTo(_15_MINUTES) <= 0;
    }
}
