package ian.parkingfeecalculator.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DailySession {
    private final Duration todayDuration;
    private final LocalDate today;
}
