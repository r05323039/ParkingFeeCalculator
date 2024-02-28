package ian.parkingfeecalculator.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DailySession {
    private final Duration dailyDuration;
    private final LocalDateTime today;
}
