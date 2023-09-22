package hackathon.project.demoservice.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZResponse <T> {
    private boolean success;
    private int code;
    private T data;
    private String message;


}
