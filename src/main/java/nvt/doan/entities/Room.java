package nvt.doan.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int roomId;
    private String roomName;
    private String bedNumbers;
    private String roomDescription;
    private double area;
    @ManyToOne
    @JoinColumn(name = "homestay_id")
    @JsonIgnore
    private Homestay homestay;
    private int status;

}
