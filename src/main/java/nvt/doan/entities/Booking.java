package nvt.doan.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;




@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Booking extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;
    private LocalDate startDate;
    private LocalDate endDate;
    //0-Empty room, 1-Booking room
    private Integer bookingStatus;
    private Double totalPriceDiscount;
    private Double depositPrice;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "userId")
    @JsonIgnore
    private User user;
    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;
    @OneToOne(mappedBy = "booking",cascade = CascadeType.ALL)
    private RoomRate roomRate;
}
