package nvt.doan.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nvt.doan.entities.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HomestayDTO{
    private int homestayId;
    private String homestayName;
    private  Address address;
    private  String description;
    private List<HomestayRate> homestayRates;
    private List<Room> rooms;

}
