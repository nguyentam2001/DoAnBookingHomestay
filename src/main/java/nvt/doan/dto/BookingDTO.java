package nvt.doan.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingDTO {
    private int requestId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String roomName;
    private double totalPrice;
}
