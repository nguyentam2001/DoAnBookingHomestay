package nvt.doan.dto;

import lombok.*;
import nvt.doan.entities.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomResponse extends Room {
    private Long totalPrice;
    private double ratePoint;
    private Address address;
}
