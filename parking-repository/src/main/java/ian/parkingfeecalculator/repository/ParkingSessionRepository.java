package ian.parkingfeecalculator.repository;

import ian.parkingfeecalculator.entity.ParkingSession;

public interface ParkingSessionRepository {
    ParkingSession find(String plate);

    void save(ParkingSession parkingSession);

    void delete(String plate);
}
