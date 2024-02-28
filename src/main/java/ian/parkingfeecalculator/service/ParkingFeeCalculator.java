package ian.parkingfeecalculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

        List<DailySession> durations = parkingInterval.getDailyDurations();

        long fee = durations.stream()
                .map(DailySession::getTodayDuration)
                .mapToLong(this::getRegularFeeDuringOnyDay).sum();
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
