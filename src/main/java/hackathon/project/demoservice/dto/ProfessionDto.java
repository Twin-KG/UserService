package hackathon.project.demoservice.dto;

import hackathon.project.demoservice.enumeration.ActiveStatus;
import hackathon.project.demoservice.enumeration.Role;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionDto {

    private Long id;

    private String username;
    private String password;
    private String email;

    private String phoneNumber;
    private String address;
    private String profileImg;
    private String coverPhoto;
    private String bio;
    private String profileLink;
    private Role role;

    private ActiveStatus activeStatus;
    private Long categoryId;
}
