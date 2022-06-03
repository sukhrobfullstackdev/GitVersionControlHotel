package uz.sukhrobdev.gitversioncontrolhotel.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class HotelDto {
    private String name;
    private String country;
    private String region;
    private String district;
    private String street;
    private String home;
    // private List<Integer> roomList;
}
