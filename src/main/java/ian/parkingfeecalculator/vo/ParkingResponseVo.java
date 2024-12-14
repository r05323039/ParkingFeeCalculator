package ian.parkingfeecalculator.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParkingResponseVo {

    private String plate;
    private int fee;
}
