package ian.parkingfeecalculator.service;

import org.junit.jupiter.api.Test;

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
        LocalDateTime start = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2024, 1, 1, 0, 14, 59);

        long actual = sut.getFee(start, end);

        assertEquals(0, actual);
    }

    @Test
    void _15_mins_fee_30() {
        LocalDateTime start = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2024, 1, 1, 0, 15, 0);

        long actual = sut.getFee(start, end);

        assertEquals(30, actual);
    }

    @Test
    void _30_mins_fee_60() {
        LocalDateTime start = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2024, 1, 1, 0, 30, 0);

        long actual = sut.getFee(start, end);

        assertEquals(60, actual);
    }
}