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
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private  int userId;
    private String fullName;
    private String username;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String gender;
    private int age;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private  Role role ;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> bookings;

}
