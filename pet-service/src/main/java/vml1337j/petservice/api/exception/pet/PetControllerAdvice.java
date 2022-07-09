package vml1337j.petservice.api.exception.pet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PetControllerAdvice {
    @ExceptionHandler({PetException.class})
    public ResponseEntity<Object> handlePetException(PetException e) {

        PetErrorDto error = new PetErrorDto(
                4000,
                e.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
