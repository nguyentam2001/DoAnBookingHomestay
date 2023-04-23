package nvt.doan.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDTO {
    String Title;
    Long number;
    private String icon;

}
