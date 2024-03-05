package ian.parkingfeecalculator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ParkingSession {
    private String plate;
    private final LocalDateTime start;
    private LocalDateTime end;

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