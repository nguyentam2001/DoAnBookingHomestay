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
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private  int userId;
    private String fullName;
    private String username;
    private String email;
    private String address;
    private int age;
    @OneToOne
    @JoinColumn(name = "role_id")
    private  Role role ;
    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;

}
