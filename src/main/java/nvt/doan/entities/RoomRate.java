package nvt.doan.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoomRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rateId;
    @Column(columnDefinition = "DECIMAL(2,1)")
    private double ratePoints;
    private String description;
    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
