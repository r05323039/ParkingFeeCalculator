package ian.parkingfeecalculator.repository;

import ian.parkingfeecalculator.entity.ParkingSession;

import java.util.HashMap;
import java.util.Map;

public class ParkingSessionRepositoryImpl implements ParkingSessionRepository {
    private Map<String, ParkingSession> parkingSessionMap = new HashMap<>();

    @Override
    public ParkingSession find(String plate) {
        return parkingSessionMap.get(plate);
    }

    @Override
    public void save(ParkingSession parkingSession) {
        parkingSessionMap.put(parkingSession.getPlate(), parkingSession);
    }
}
