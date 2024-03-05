package ian.parkingfeecalculator.repository;

import ian.parkingfeecalculator.entity.ParkingSession;

public interface ParkingSessionRepository {
    ParkingSession find();

    void save(ParkingSession parkingSession);
}
