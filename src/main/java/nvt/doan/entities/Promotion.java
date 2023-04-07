package nvt.doan.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Promotion extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer promotionId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String promotionName;
    private String description;
    private Double percentDiscount;
    @ManyToOne
    @JoinColumn(name = "homestay_id")
    private Homestay homestay;
}
