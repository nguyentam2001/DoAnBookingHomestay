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
public class Homestay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int homestayId;
    private String homestayName;
    private int homestayType;
    private  boolean status;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private  Address address;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Booking booking;

    private  String description;

    @OneToMany(mappedBy ="homestay")
    private List<HomestayRate> homestayRates;
    @OneToMany(mappedBy ="homestay")
    private List<Room> rooms;
}
