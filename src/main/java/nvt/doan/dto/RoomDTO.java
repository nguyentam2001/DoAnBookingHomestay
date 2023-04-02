package nvt.doan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nvt.doan.entities.Homestay;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomDTO {
    private  Integer roomId;
    private String roomName;
    private String bedNumbers;
    private String roomDescription;
    private Double area;
    private Integer homestayId;
    private Integer roomType;
    private  Boolean status;
    private Integer numberOfPerson;
    private Double price;
}
