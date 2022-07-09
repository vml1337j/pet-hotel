package vml1337j.registerservice.api.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationErrorDto {
    @JsonProperty(value = "error_code")
    private int errorCode;

    @JsonProperty(value = "error_msg")
    private String errorMsg;
}
