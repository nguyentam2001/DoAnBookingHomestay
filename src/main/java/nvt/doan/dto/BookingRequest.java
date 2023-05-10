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
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingRequest {
    private int requestId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer bookingStatus;
    private Integer numberPersons;
    private LocalDate lastDayCancel;
    private Integer totalDate;
    //cancellation cost ( Chi phí huỷ)
    private Double cancellationCost;
    private Double depositPrice;
    private Double actualPayment;
    private Double totalPriceDiscount;
    private LocalDateTime bookingTime;
    private LocalDateTime cancelTime;
    private String reason;
    private User user;
    private Room room;
    private RoomRate roomRate;
}
