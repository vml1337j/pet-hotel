package vml1337j.hotelapp.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@RestController
public class UserValidationController {
    public static final String VALIDATE_USERNAME = "/validation";

    private final RestTemplate restTemplate;

    @Value("${api.validation-service}")
    private String VALIDATION_SERVICE;

    @GetMapping(VALIDATE_USERNAME)
    public ResponseEntity validateUsername(@RequestParam String username) {
        String response = restTemplate.getForObject(
                String.format(VALIDATION_SERVICE + "?username=%s", username), String.class
        );

        return ResponseEntity.ok(response);
    }
}
