package ian.parkingfeecalculator.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ParkingRequestVo {
    @NotBlank
    private String plate;
}
