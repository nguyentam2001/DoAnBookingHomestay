package nvt.doan.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nvt.doan.entities.Room;
import nvt.doan.entities.RoomRate;
import nvt.doan.entities.User;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingRequest {
    private int requestId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer bookingStatus;
    private Double depositPrice;
    private Double totalPriceDiscount;
    private User user;
    private Room room;
    private RoomRate roomRate;
}
