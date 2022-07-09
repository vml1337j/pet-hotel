package vml1337j.hotelapp.service.authorization.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vml1337j.hotelapp.service.authorization.dto.AuthDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizationController {
    public static final String AUTHORIZATION = "/auth";
    public static final String LOGOUT = "/logout";

    @PostMapping(AUTHORIZATION)
    public ResponseEntity authorization(@RequestBody AuthDto cred,
                                        HttpServletRequest request) throws ServletException {

        request.login(cred.getUsername(), cred.getPassword());
        return ResponseEntity.ok("User is authorized");
    }

    @PostMapping(LOGOUT)
    public ResponseEntity logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return ResponseEntity.ok("User is logged out");
    }

}
