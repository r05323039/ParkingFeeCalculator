package ian.parkingfeecalculator.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ParkingInterval {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public List<DailySession> getDailySessions() {
        List<DailySession> dailySessions = new ArrayList<>();
        LocalDate today = start.toLocalDate();
        LocalDateTime todayStart = today.atStartOfDay();
        while (todayStart.isBefore(end)) {
            LocalDateTime tomorrow = todayStart.plusDays(1);

            LocalDateTime intervalStart = start.isAfter(todayStart) ?
                    start : todayStart;
            LocalDateTime intervalEnd = end.isBefore(tomorrow) ?
                    end : tomorrow;

            Duration todayDuration = Duration.between(intervalStart, intervalEnd);
            dailySessions.add(new DailySession(todayDuration, today));
            todayStart = tomorrow;
        }
        return dailySessions;
    }

    public Duration getTotalDuration() {
        return Duration.between(start, end);
    }
}