package ian.parkingfeecalculator.service;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingFeeCalculator {
    public long getFee(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        long minutes = duration.toMinutes();
        long intervalAmount = minutes / 15;
        if (intervalAmount < 1)
            return 0;
        return intervalAmount * 30;
    }
}
