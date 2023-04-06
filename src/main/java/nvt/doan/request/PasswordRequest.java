package nvt.doan.request;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordRequest {
    @Size(min = 3)
    private String oldPassword;
    @Size(min = 3)
    private String newPassword;
}
