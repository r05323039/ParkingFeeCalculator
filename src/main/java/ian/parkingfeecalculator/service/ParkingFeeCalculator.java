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
        fee = getRegularFee(duration);

        return Math.min(fee, 150);
    }

    private long getRegularFee(Duration duration) {
        long fee;
        long interval = BigDecimal.valueOf(duration.toNanos())
                .divide(BigDecimal.valueOf(_30_MINUTES.toNanos())
                        , 0, RoundingMode.UP).longValue();
        fee = interval * 30;
        return fee;
    }

    private boolean isFreeInterval(Duration duration) {
        return duration.compareTo(_15_MINUTES) <= 0;
    }
}
