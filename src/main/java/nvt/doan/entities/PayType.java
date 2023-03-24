package nvt.doan.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PayType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payTypeId;

    @OneToMany(mappedBy = "payType")
    private List<BillPay> billpayList;
    private String type;
}
