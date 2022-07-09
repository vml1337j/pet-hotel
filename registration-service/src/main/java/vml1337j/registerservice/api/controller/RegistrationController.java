package vml1337j.registerservice.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vml1337j.registerservice.api.dto.UserDto;
import vml1337j.registerservice.api.exception.UsernameAlreadyTakenException;
import vml1337j.registerservice.store.User;
import vml1337j.registerservice.store.UserRepository;

@RequiredArgsConstructor
@RestController
public class RegistrationController {
    public static final String REGISTRATION = "/api/v1/registration";

    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;

    @PostMapping(REGISTRATION)
    public ResponseEntity registerUser(@RequestBody @NonNull UserDto userDto) {

        boolean userExists = userRepository.existsByUsername(userDto.getUsername());

        if (userExists) {
            throw new UsernameAlreadyTakenException(
                    String.format("Username \"%s\" is taken", userDto.getUsername())
            );
        }

        userRepository.save(createUser(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private User createUser(UserDto user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        return User.builder()
                .username(user.getUsername())
                .password(encodedPassword)
                .roles("ROLE_USER")
                .build();
    }
}
