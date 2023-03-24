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
public class BillPay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billPayId;

    @OneToOne
    @JoinColumn(name = "bill_id")
    private  Bill bill;

    @ManyToOne
    private  PayType payType;
}
