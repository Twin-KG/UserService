package hackathon.project.demoservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccessType {
    private Long id;
    private String name;
    private short rank;
}
