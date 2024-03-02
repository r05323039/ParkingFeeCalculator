package ian.parkingfeecalculator.repository;

import ian.parkingfeecalculator.entity.ParkingInterval;

public class ParkingIntervalRepository {
    private ParkingInterval parkingInterval;

    public ParkingInterval find() {
        return parkingInterval;
    }

    public void save(ParkingInterval parkingInterval) {
        this.parkingInterval = parkingInterval;
    }
}
