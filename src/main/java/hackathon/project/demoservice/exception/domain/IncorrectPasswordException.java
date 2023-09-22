package hackathon.project.demoservice.exception.domain;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException(String message){
        super(message);
    }
}
