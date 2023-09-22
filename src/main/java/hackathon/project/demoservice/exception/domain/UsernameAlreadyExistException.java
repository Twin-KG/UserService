package hackathon.project.demoservice.exception.domain;

public class UsernameAlreadyExistException extends RuntimeException{
    public UsernameAlreadyExistException(String message){
        super(message);
    }
}
