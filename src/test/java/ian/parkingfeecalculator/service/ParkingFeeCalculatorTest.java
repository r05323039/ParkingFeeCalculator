package ian.parkingfeecalculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
    private ParkingFeeCalculator sut;
    private LocalDateTime start;
    private LocalDateTime end;
    private long actual;

    @BeforeEach
    void setUp() {
        sut = new ParkingFeeCalculator(new CalendarRepository());
    }

    private void assert_fee_is(int expected) {
        assertEquals(expected, actual);
    }

    private void given_parking_end(String endText) {
        end = LocalDateTime.parse(endText);
    }

    private void given_parking_start(String startText) {
        start = LocalDateTime.parse(startText);
    }

    private void calculate_fee() {
        actual = sut.getFee(new ParkingInterval(start, end));
    }

    @Test
    void below_15_mins_free() {
        given_parking_start("2024-01-02T00:00:00");
        given_parking_end("2024-01-02T00:15:00");
        calculate_fee();
        assert_fee_is(0);
    }

    @Test
    void over_15_mins_fee_30() {
        given_parking_start("2024-01-02T00:00:00");
        given_parking_end("2024-01-02T00:15:01");
        calculate_fee();
        assert_fee_is(30);
    }

    @Test
    void _30_mins_fee_30() {
        given_parking_start("2024-01-02T00:00:00");
        given_parking_end("2024-01-02T00:30:00");
        calculate_fee();
        assert_fee_is(30);
    }
    @Test
    void over_30_mins_fee_60() {
        given_parking_start("2024-01-02T00:00:00");
        given_parking_end("2024-01-02T00:30:01");
        calculate_fee();
        assert_fee_is(60);
    }

    @Test
    void over_60_mins_fee_90() {
        given_parking_start("2024-01-02T00:00:00");
        given_parking_end("2024-01-02T01:00:01");
        calculate_fee();
        assert_fee_is(90);
    }

    @Test
    void regular_ceiling_fee_150() {
        given_parking_start("2024-01-02T00:00:00");
        given_parking_end("2024-01-02T23:59:59");
        calculate_fee();
        assert_fee_is(150);
    }

    @Test
    void over_one_day() {
        given_parking_start("2024-01-02T00:00:00");
        given_parking_end("2024-01-03T00:00:01");
        calculate_fee();
        assert_fee_is(180);
    }

    @Test
    void partial_day_and_whole_day() {
        given_parking_start("2024-01-02T23:50:00");
        given_parking_end("2024-01-04T00:00:00");
        calculate_fee();
        assert_fee_is(30+150);
    }

    @Test
    void whole_day_and_partial_day() {
        given_parking_start("2024-01-02T00:00:00");
        given_parking_end("2024-01-03T00:10:00");
        calculate_fee();
        assert_fee_is(150+30);
    }

    @Test
    void parital_day_and_partial_day() {
        given_parking_start("2024-01-02T01:00:00");
        given_parking_end("2024-01-03T01:10:00");
        calculate_fee();
        assert_fee_is(150+90);
    }
    @Test
    @Disabled
    void test() {
        LocalDateTime start = LocalDateTime.parse("2024-01-02T00:00:00");
        System.out.println(start.toLocalDate().atStartOfDay());
    }

    @Test
    void saturday_over_15_mins_fee_50() {
        given_parking_start("2024-01-06T00:00:00");
        given_parking_end("2024-01-06T00:15:01");
        calculate_fee();
        assert_fee_is(50);
    }
    @Test
    void sunday_over_15_mins_fee_50() {
        given_parking_start("2024-01-07T00:00:00");
        given_parking_end("2024-01-07T00:15:01");
        calculate_fee();
        assert_fee_is(50);
    }
    @Test
    void national_holiday_over_15_mins_fee_50() {
        given_parking_start("2024-02-28T00:00:00");
        given_parking_end("2024-02-28T00:15:01");
        calculate_fee();
        assert_fee_is(50);
    }
    @Test
    void holiday_ceiling_fee_2400() {
        given_parking_start("2024-01-06T00:00:00");
        given_parking_end("2024-01-07T00:00:00");
        calculate_fee();
        assert_fee_is(2400);
    }
}