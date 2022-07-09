package vml1337j.hotelapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PetHotelBasicException extends RuntimeException {
    private HttpStatus statusCode;
    private int apiErrorCode;
    private String message;

    public PetHotelBasicException(HttpStatus statusCode, ErrorDto error) {
        super(error.getErrorMsg());
        this.statusCode = statusCode;
        this.apiErrorCode = error.getErrorCode();
        this.message = error.getErrorMsg();
    }
}
