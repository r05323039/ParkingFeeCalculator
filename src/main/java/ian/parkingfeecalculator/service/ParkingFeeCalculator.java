package ian.parkingfeecalculator.service;

import ian.parkingfeecalculator.repository.ParkingSessionRepository;
import ian.parkingfeecalculator.repository.CalendarRepository;
import ian.parkingfeecalculator.entity.DailySession;
import ian.parkingfeecalculator.entity.ParkingSession;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;

public class ParkingFeeCalculator {

    private final Duration _15_MINUTES = Duration.ofMinutes(15);
    private final Duration _30_MINUTES = Duration.ofMinutes(30);
    private final CalendarRepository calendarRepository;
    private final ParkingSessionRepository parkingSessionRepository;

    public ParkingFeeCalculator(CalendarRepository calendarRepository, ParkingSessionRepository repository) {
        this.calendarRepository = calendarRepository;
        this.parkingSessionRepository = repository;
    }

    public long getFee(String plate) {
        ParkingSession parkingSession = parkingSessionRepository.find(plate);

        return getFee(parkingSession);
    }

    public long getFee(ParkingSession parkingSession) {
        if (parkingSession == null) {
            return 0;
        }
        Duration duration = parkingSession.getTotalDuration();
        if (isFreeInterval(duration)) {
            return 0;
        }

        List<DailySession> dailySessions = parkingSession.getDailySessions();

        long totalFee = dailySessions.stream()
                .mapToLong(this::getRegularFeeDuringOnyDay).sum();
        return totalFee;
    }

    private long getRegularFeeDuringOnyDay(DailySession dailySession) {
        Duration duration = dailySession.getTodayDuration();

        long intervals = BigDecimal.valueOf(duration.toNanos())
                .divide(BigDecimal.valueOf(_30_MINUTES.toNanos())
                        , 0, RoundingMode.UP).longValue();

        boolean isHoliday = calendarRepository.getCalendar().isHoliday(dailySession.getToday());
        long feePerHalfHour = isHoliday ? 50 : 30;
        long todayFee = intervals * feePerHalfHour;

        return isHoliday ? Math.min(todayFee, 2400) : Math.min(todayFee, 150);
    }

    private boolean isFreeInterval(Duration duration) {
        return duration.compareTo(_15_MINUTES) <= 0;
    }
}
