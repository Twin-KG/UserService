package hackathon.project.demoservice.domain;

import hackathon.project.demoservice.dto.ProfessionDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String tokenType;
    private ProfessionDto user;
    private String message;
}
