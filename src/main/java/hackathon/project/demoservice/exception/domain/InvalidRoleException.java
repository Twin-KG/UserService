package hackathon.project.demoservice.exception.domain;

public class InvalidRoleException extends RuntimeException {
    public InvalidRoleException(String message){
        super(message);
    }
}
