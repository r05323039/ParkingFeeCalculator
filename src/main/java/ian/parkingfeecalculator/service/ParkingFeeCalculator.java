package ian.parkingfeecalculator.service;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingFeeCalculator {
    public long getFee(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        if (duration.minusMinutes(15).isNegative())
            return 0;
        return 30;
    }
}
