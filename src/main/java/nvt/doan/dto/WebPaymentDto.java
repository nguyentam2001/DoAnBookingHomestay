package nvt.doan.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WebPaymentDto {
    private String status;
    private String message;
    private String url;

}
