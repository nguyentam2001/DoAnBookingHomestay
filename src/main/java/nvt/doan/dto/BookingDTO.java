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
    private String homestayName;
    private int numberOfRoom;
    private double price;
    private String addressName;
}
