package vml1337j.petservice.api.exception.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PetErrorDto {
    @JsonProperty(value = "error_code")
    private int errorCode;

    @JsonProperty(value = "error_msg")
    private String errorMsg;
}
