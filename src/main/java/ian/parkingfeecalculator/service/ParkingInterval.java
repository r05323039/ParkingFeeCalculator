package ian.parkingfeecalculator.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ParkingInterval {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public List<Duration> getDailyDurations() {
        List<Duration> durations = new ArrayList<>();
        LocalDateTime todayStart = start.toLocalDate().atStartOfDay();
        while (todayStart.isBefore(end)) {
            LocalDateTime tomorrowStart = todayStart.plusDays(1);

            LocalDateTime intervalStart = start.isAfter(todayStart) ?
                    start : todayStart;
            LocalDateTime intervalEnd = end.isBefore(tomorrowStart) ?
                    end : tomorrowStart;

            Duration dailyDuration = Duration.between(intervalStart, intervalEnd);
            durations.add(dailyDuration);
            todayStart = tomorrowStart;
        }
        return durations;
    }

    public Duration getTotalDuration() {
        return Duration.between(start, end);
    }
}