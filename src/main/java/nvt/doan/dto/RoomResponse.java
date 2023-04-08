package nvt.doan.dto;

import lombok.*;
import nvt.doan.entities.Booking;
import nvt.doan.entities.FileData;
import nvt.doan.entities.Homestay;
import nvt.doan.entities.Room;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomResponse extends Room {
    private Long totalPrice;
    private double ratePoint;
}
