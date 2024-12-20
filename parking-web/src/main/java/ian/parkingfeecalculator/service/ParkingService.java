package ian.parkingfeecalculator.service;

import ian.parkingfeecalculator.entity.ParkingSession;
import ian.parkingfeecalculator.repository.ParkingSessionRepository;
import ian.parkingfeecalculator.repository.TaiwanCalendarRepository;
import ian.parkingfeecalculator.vo.ParkingRequestVo;
import ian.parkingfeecalculator.vo.ParkingResponseVo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ParkingService {
    private final ParkingSessionRepository parkingSessionRepository;

    public ParkingResponseVo startParking(@Valid @RequestBody ParkingRequestVo request) {
        String plate = request.getPlate();
        if (parkingSessionRepository.find(plate) != null) {
            throw new RuntimeException(plate + " had parked already.");
        }

        LocalDateTime startTime = LocalDateTime.now();
        parkingSessionRepository.save(ParkingSession.start(plate, startTime));

        ParkingResponseVo response = new ParkingResponseVo();
        response.setPlate(plate);
        response.setStartTime(startTime);
        return response;
    }

    public ParkingResponseVo endParking(@Valid @RequestBody ParkingRequestVo request) {
        String plate = request.getPlate();
        ParkingSession parkingSession = parkingSessionRepository.find(plate);
        if (parkingSession == null) {
            throw new RuntimeException(plate + " not found.");
        }
        LocalDateTime endTime = LocalDateTime.now().plusHours(3);
        parkingSession.setEnd(endTime);

        ParkingFeeCalculator taiwanParkingFeeCalculator = new ParkingFeeCalculator(new TaiwanCalendarRepository(), parkingSessionRepository);
        parkingSessionRepository.delete(plate);
        ParkingResponseVo response = new ParkingResponseVo();
        response.setPlate(plate);
        response.setFee(taiwanParkingFeeCalculator.getFee(parkingSession));
        response.setStartTime(parkingSession.getStart());
        response.setEndTime(endTime);
        return response;
    }
}
