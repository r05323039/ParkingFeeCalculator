package ian.parkingfeecalculator.controller;

import ian.parkingfeecalculator.service.ParkingService;
import ian.parkingfeecalculator.vo.ParkingRequestVo;
import ian.parkingfeecalculator.vo.ParkingResponseVo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking")
@RequiredArgsConstructor
public class ParkingFeeController {

    private final ParkingService parkingService;

    @PostMapping("/start")
    public ResponseEntity<ParkingResponseVo> startParking(@Valid @RequestBody ParkingRequestVo request) {
        return ResponseEntity.ok()
                .body(parkingService.startParking(request));
    }

    @PostMapping("/end")
    public ResponseEntity<ParkingResponseVo> endParking(@Valid @RequestBody ParkingRequestVo request) {
        return ResponseEntity.ok()
                .body(parkingService.endParking(request));
    }
}
