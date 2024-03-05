package ian.parkingfeecalculator.repository;

import ian.parkingfeecalculator.entity.ParkingSession;

public class ParkingSessionRepository {
    private ParkingSession parkingSession;

    public ParkingSession find() {
        return parkingSession;
    }

    public void save(ParkingSession parkingSession) {
        this.parkingSession = parkingSession;
    }
}
