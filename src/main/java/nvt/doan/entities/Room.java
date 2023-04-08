package nvt.doan.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int roomId;
    private String roomName;
    private String bedNumbers;
    private String roomDescription;
    private double area;
    private int roomType;
    private  boolean status;
    private int numberOfPerson;
    @ManyToOne
    @JoinColumn(name = "homestay_id")
    private Homestay homestay;
    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    private List<FileData> images;
    @OneToMany(mappedBy = "room")
    private List<Booking> bookingList;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private double price;



}
