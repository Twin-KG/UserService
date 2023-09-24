package hackathon.project.demoservice.exception;

import hackathon.project.demoservice.domain.ZResponse;
import hackathon.project.demoservice.dto.ResponseBuilder;
import hackathon.project.demoservice.exception.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import static hackathon.project.demoservice.constant.UserConstant.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(UsernameAlreadyExistException.class)
    public ResponseEntity<ZResponse> usernameAlreadyExistException() {
        return createHttpResponse(CONFLICT, USERNAME_ALREADY_EXIST);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<ZResponse> emailAlreadyExistException() {
        return createHttpResponse(CONFLICT, EMAIL_ALREADY_EXIST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ZResponse> userNotFoundException() {
        return createHttpResponse(NO_CONTENT, USER_NOT_FOUND_BY_USERNAME);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ZResponse> usernameNotFoundException() {
        return createHttpResponse(NO_CONTENT, USER_NOT_FOUND_BY_USERNAME);
    }

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<ZResponse> invalidRoleException() {
        return createHttpResponse(BAD_REQUEST, INVALID_ROLE);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ZResponse> invalidPasswordException() {
        return createHttpResponse(BAD_REQUEST, INVALID_PASSWORD);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ZResponse> noHandlerFoundException() {
        return createHttpResponse(BAD_REQUEST, "No such path found");
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ZResponse> dataNotFoundException(Exception e) {
        return createHttpResponse(NO_CONTENT, e.getMessage());
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ZResponse> incorrectPasswordException(Exception e) {
        return createHttpResponse(BAD_REQUEST, e.getMessage());
    }

    private ResponseEntity<ZResponse> createHttpResponse(HttpStatus httpStatus, String message){
        ZResponse response = ResponseBuilder.build(false, httpStatus, message, null);
        return new ResponseEntity<>(response, httpStatus);
    }
}
