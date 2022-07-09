package vml1337j.hotelapp.service.registration.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import vml1337j.hotelapp.service.registration.dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class RegistrationController {

    public static final String USER_REGISTRATION = "/registration";

    @Value("${api.registration-service}")
    private String REGISTRATION_SERVICE;

    private final RestTemplate restTemplate;

    @PostMapping(USER_REGISTRATION)
    public ResponseEntity registerNewUser(@RequestBody UserDto user,
                                          HttpServletRequest httpServletRequest) throws ServletException {
        restTemplate.postForLocation(REGISTRATION_SERVICE, user);

        httpServletRequest.login(user.getUsername(), user.getPassword());
        return ResponseEntity.ok("User registered and logged in");
    }
}
