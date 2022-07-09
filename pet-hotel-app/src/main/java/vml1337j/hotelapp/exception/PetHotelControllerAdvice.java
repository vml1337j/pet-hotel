package vml1337j.hotelapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class PetHotelControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler({PetHotelApiException.class, PetHotelAuthorizationException.class})
    ResponseEntity<ErrorResponse> handlePetHotelApiException(PetHotelBasicException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(ex, request.getRequestURI()),
                ex.getStatusCode()
        );
    }
}
