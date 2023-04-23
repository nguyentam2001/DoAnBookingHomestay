package nvt.doan.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReportDTO {
    Integer homestayId;
    LocalDate startDate;
    LocalDate endDate;
    List<BookingRequest> bookingRequestList;
    Double  totalPrice;

}
