package vml1337j.hotelapp.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {
    @JsonProperty(value = "error_code")
    private int errorCode;

    @JsonProperty(value = "error_msg")
    private String errorMsg;
}

