package nvt.doan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nvt.doan.entities.Booking;
import nvt.doan.entities.Room;
import nvt.doan.entities.User;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//Kiểm tra xem người người này đã đánh giá phòng này chưa nếu chưa thì thêm mới đánh giá, ngược lại thì up date
public class RateDTO {
    private Integer requestId;
    private Integer userId;
    private Integer roomId;
    private Integer rateId;
    private Double ratePoints;
    private String description;
}
