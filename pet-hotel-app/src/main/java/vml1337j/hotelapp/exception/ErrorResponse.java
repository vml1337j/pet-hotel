package vml1337j.hotelapp.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ErrorResponse {
    private String timestamp;
    private int status;
    private String error;
    private String path;
    private String message;
    @JsonProperty("api_error_code")
    private int apiErrorCode;

    public ErrorResponse(PetHotelBasicException ex, String path) {
        this.timestamp = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
        this.status = ex.getStatusCode().value();
        this.error = ex.getStatusCode().getReasonPhrase();
        this.message = ex.getMessage();
        this.apiErrorCode = ex.getApiErrorCode();
        this.path = path;
    }
}
