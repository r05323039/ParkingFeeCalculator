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

        long parkingDays = duration.dividedBy(Duration.ofDays(1));
        Duration remainingDuration = duration.minus(Duration.ofDays(1).multipliedBy(parkingDays));
        fee = 150 * parkingDays + getRegularFee(remainingDuration);

        return fee;
    }

    private long getRegularFee(Duration duration) {
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
