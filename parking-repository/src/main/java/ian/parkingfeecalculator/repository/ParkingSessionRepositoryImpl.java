package ian.parkingfeecalculator.repository;

import ian.parkingfeecalculator.entity.ParkingSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
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

    @Override
    public void delete(String plate) {
        parkingSessionMap.remove(plate);
    }
}
