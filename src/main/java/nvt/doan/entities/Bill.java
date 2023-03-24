package nvt.doan.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int BillId;
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
    private LocalDate dateBill;

    @Column(columnDefinition = "DECIMAL(10,2)")
    private Double total;
}
