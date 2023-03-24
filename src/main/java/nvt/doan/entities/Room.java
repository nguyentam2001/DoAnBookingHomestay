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
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int roomId;
    private String roomName;
    private String bedNumbers;
    private String description;
    @ManyToOne
    @JoinColumn(name = "homestay_id")
    private Homestay homestay;
    private int status;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private double price;
}
