package nvt.doan.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import nvt.doan.entities.Address;
import nvt.doan.entities.HomestayRate;
import nvt.doan.entities.Room;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class HomestayClientDTO {
    private int homestayId;
    private String homestayName;
    private  String description;
    private  Integer roomAvailable;
}
