package vml1337j.validationservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vml1337j.validationservice.store.UserRepository;

@RequiredArgsConstructor
@RestController
public class ValidationController {
    public static final String USERNAME_VALIDATION = "/api/v1/validation";

    private final UserRepository userRepository;

    @GetMapping(USERNAME_VALIDATION)
    public ResponseEntity usernameValidation(@RequestParam String username) {
        if (userRepository.existsByUsername(username)) {
            return ResponseEntity.ok("Username taken");
        }

        return ResponseEntity.ok("Username free");
    }
}
