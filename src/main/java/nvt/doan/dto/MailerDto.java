package nvt.doan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
//thông tin người đưa thư
public class MailerDto {
    private String email;
    private String name;
}
