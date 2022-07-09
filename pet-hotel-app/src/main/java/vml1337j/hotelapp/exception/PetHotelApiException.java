package vml1337j.hotelapp.exception;

import org.springframework.http.HttpStatus;

public class PetHotelApiException extends PetHotelBasicException {
    public PetHotelApiException(HttpStatus statusCode, ErrorDto error) {
        super(statusCode, error);
    }
}
