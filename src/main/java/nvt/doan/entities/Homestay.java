package nvt.doan.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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
    @ManyToOne
    @JoinColumn(name = "address_id")
    private  Address address;

    private  String addressDetails;
    private  String description;
    @OneToMany(mappedBy ="homestay")
    @JsonIgnore
    private List<HomestayRate> homestayRates;
    @OneToMany(mappedBy ="homestay",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Room> rooms;
    @OneToMany(mappedBy ="homestay",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Promotion> promotions;



}
