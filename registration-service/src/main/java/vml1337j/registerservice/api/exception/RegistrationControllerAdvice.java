package vml1337j.registerservice.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RegistrationControllerAdvice {

    @ExceptionHandler({UsernameAlreadyTakenException.class})
    public ResponseEntity<Object> handleOwnerException(UsernameAlreadyTakenException e) {
        RegistrationErrorDto error = new RegistrationErrorDto(
                4000,
                e.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
