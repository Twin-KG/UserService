package hackathon.project.demoservice.dto;

import hackathon.project.demoservice.domain.AccessType;
import hackathon.project.demoservice.domain.Tier;
import hackathon.project.demoservice.enumeration.ActiveStatus;
import hackathon.project.demoservice.enumeration.Role;
import hackathon.project.demoservice.model.Category;
import lombok.*;

import java.util.List;

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
    private String embedUrl;

    private ActiveStatus activeStatus;
    private List<Tier> tierList;
    private Long categoryId;
    private Category category;
}
