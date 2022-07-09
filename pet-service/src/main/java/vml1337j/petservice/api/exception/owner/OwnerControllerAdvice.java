package vml1337j.petservice.api.exception.owner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OwnerControllerAdvice {
    @ExceptionHandler({OwnerException.class})
    public ResponseEntity<Object> handleOwnerException(OwnerException e) {

        OwnerErrorDto error = new OwnerErrorDto(
                4000,
                e.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
