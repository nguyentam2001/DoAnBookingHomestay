package nvt.doan.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @OneToMany(mappedBy = "homestay")
    private List<Booking> bookingList;
    private  String description;

    @Column(columnDefinition = "DECIMAL(10,2)")
    private double price;

    @OneToMany(mappedBy ="homestay")
    @JsonIgnore
    private List<HomestayRate> homestayRates;
    @OneToMany(mappedBy ="homestay",cascade = CascadeType.ALL)
    private List<Room> rooms;
}
