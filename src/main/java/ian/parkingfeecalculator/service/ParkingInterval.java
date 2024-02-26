package ian.parkingfeecalculator.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@Data
@RequiredArgsConstructor
public class ParkingInterval {
    private final LocalDateTime start;
    private final LocalDateTime end;
}