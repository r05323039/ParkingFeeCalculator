package ian.parkingfeecalculator.service;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 15分內免費
 * 平日 / 假日與國定假日
 * 每小時60 / 100 (以半小時計)
 * 當日上限150 / 2400 (隔日另計)
 */
class ParkingFeeCalculatorTest {
    private ParkingFeeCalculator sut = new ParkingFeeCalculator();

    @Test
    void below_15_mins_free() {
        LocalDateTime start = LocalDateTime.parse("2024-01-01T00:00:00");
        LocalDateTime end = LocalDateTime.parse("2024-01-01T00:14:59");

        long actual = sut.getFee(start, end);

        assertEquals(0, actual);
    }

    @Test
    void _15_mins_fee_30() {
        LocalDateTime start = LocalDateTime.parse("2024-01-01T00:00:00");
        LocalDateTime end = LocalDateTime.parse("2024-01-01T00:15:00");
        long actual = sut.getFee(start, end);

        assertEquals(30, actual);
    }
    @Test
    void _30_mins_fee_60() {
        LocalDateTime start = LocalDateTime.parse("2024-01-01T00:00:00");
        LocalDateTime end = LocalDateTime.parse("2024-01-01T00:30:00");
        long actual = sut.getFee(start, end);

        assertEquals(60, actual);
    }

    @Test
    void _60_mins_fee_90() {
        LocalDateTime start = LocalDateTime.parse("2024-01-01T00:00:00");
        LocalDateTime end = LocalDateTime.parse("2024-01-01T01:00:00");
        long actual = sut.getFee(start, end);

        assertEquals(90, actual);
    }

    @Test
    void regular_ceiling_fee_150() {
        LocalDateTime start = LocalDateTime.parse("2024-01-01T00:00:00");
        LocalDateTime end = LocalDateTime.parse("2024-01-01T23:59:59");
        long actual = sut.getFee(start, end);

        assertEquals(150, actual);

    }
}