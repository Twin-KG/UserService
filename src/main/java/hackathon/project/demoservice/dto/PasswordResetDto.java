package hackathon.project.demoservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetDto {
    private String oldPassword;
    private String newPassword;
}
