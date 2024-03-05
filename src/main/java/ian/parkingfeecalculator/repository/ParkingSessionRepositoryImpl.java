package ian.parkingfeecalculator.repository;

import ian.parkingfeecalculator.entity.ParkingSession;

public class ParkingSessionRepositoryImpl implements ParkingSessionRepository {
    private ParkingSession parkingSession;

    @Override
    public ParkingSession find() {
        return parkingSession;
    }

    @Override
    public void save(ParkingSession parkingSession) {
        this.parkingSession = parkingSession;
    }
}
