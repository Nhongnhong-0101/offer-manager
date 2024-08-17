package com.example.offer_management_be.controller;

import com.example.offer_management_be.dto.LoginUserDto;
import com.example.offer_management_be.dto.RegisterUserDto;
import com.example.offer_management_be.exception.UserNotFoundException;
import com.example.offer_management_be.models.LoginResponse;
import com.example.offer_management_be.models.User;
import com.example.offer_management_be.response.ExceptionResponse;
import com.example.offer_management_be.response.UserResponse;
import com.example.offer_management_be.service.AuthenticationService;
import com.example.offer_management_be.service.JwtService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;
    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signUp(registerUserDto);


        return ResponseEntity.ok(registeredUser);

    }

    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody LoginUserDto loginUserDto) {

        try {
            User authenticatedUser = authenticationService.authenticate(loginUserDto);

            String jwtToken = jwtService.generateToken(authenticatedUser);

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setStatusCode(HttpStatus.OK.value());
            loginResponse.setToken(jwtToken);
            loginResponse.setExpiresIn(jwtService.getExpirationTime());

            return ResponseEntity.ok().body(loginResponse);

        }
        catch (Exception e){
            ExceptionResponse response = new ExceptionResponse();
            response.setMessage(e.toString());
            response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
            logger.info(e.toString());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
