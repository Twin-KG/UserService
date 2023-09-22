package hackathon.project.demoservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
}
