package nvt.doan.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HomestayRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rateId;

    @ManyToOne
    @JoinColumn(name = "homestay_id")
    private Homestay homestay;

    private int rateCode;
    private String description;

}
