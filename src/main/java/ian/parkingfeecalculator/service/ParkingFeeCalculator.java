package ian.parkingfeecalculator.service;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingFeeCalculator {
    public long getFee(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        long minutes = duration.toMinutes();

        return getRegularFee(minutes);
    }

    private long getRegularFee(long minutes) {
        long fee = 0;
        if (minutes < 15)
            return fee;

        long intervalAmount = minutes / 30;

        fee = (intervalAmount + 1) * 30;
        return Math.min(fee, 150);
    }
}
