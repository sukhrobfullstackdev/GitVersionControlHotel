package uz.sukhrobdev.gitversioncontrolhotel.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RoomDto {
    private String number;
    private String floor;
    private String size;
    private Integer hotelId;
}
