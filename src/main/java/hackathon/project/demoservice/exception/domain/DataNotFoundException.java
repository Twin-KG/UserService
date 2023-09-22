package hackathon.project.demoservice.exception.domain;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message){
        super(message);
    }
}
