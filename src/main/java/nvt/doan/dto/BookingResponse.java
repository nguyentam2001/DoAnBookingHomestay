package nvt.doan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nvt.doan.entities.Promotion;
import nvt.doan.entities.User;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingResponse extends RoomResponse {
        private User user;
        private Promotion promotion;
        private LocalDate startDate;
        private LocalDate endDate;
        private Integer totalDate;
        //Bàng tổng giá nhân với phần trăm giảm nếu có
        private double totalPriceDiscount;
        private Integer numberPersons;
        private LocalDate lastDayCancel;
}
