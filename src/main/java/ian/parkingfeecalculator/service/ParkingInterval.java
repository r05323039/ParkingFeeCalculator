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

    public List<DailySession> getDailyDurations() {
        List<DailySession> dailySessions = new ArrayList<>();
        LocalDateTime today = start.toLocalDate().atStartOfDay();
        while (today.isBefore(end)) {
            LocalDateTime tomorrow = today.plusDays(1);

            LocalDateTime intervalStart = start.isAfter(today) ?
                    start : today;
            LocalDateTime intervalEnd = end.isBefore(tomorrow) ?
                    end : tomorrow;

            Duration dailyDuration = Duration.between(intervalStart, intervalEnd);
            dailySessions.add(new DailySession(dailyDuration,today));
            today = tomorrow;
        }
        return dailySessions;
    }

    public Duration getTotalDuration() {
        return Duration.between(start, end);
    }
}