package hackathon.project.demoservice.model;

import hackathon.project.demoservice.enumeration.ActiveStatus;
import hackathon.project.demoservice.enumeration.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Professions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;
    private String password;

    @Column(unique = true)
    private String email;

    private String phoneNumber;
    private String address;
    private String profileImg;
    private String coverPhoto;
    private String bio;
    private String profileLink;
    private String embeddedUrl;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private ActiveStatus activeStatus;

    @OneToMany(mappedBy = "profession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SocialLink> socialLinkList;

    public void addSocialLink(SocialLink socialLink){
        if(socialLinkList == null){
            socialLinkList = new ArrayList<>();
        }

        socialLink.setProfession(this);
        socialLinkList.add(socialLink);
    }

}
