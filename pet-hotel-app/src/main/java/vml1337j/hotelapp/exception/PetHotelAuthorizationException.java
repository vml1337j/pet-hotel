package vml1337j.hotelapp.exception;

import org.springframework.http.HttpStatus;

public class PetHotelAuthorizationException extends PetHotelBasicException {
    public PetHotelAuthorizationException(HttpStatus statusCode, ErrorDto error) {
        super(statusCode, error);
    }
}
