package com.example.offer_management_be.service;

import com.example.offer_management_be.dto.LoginUserDto;
import com.example.offer_management_be.dto.RegisterUserDto;
import com.example.offer_management_be.models.User;
import com.example.offer_management_be.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository  userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User signUp(RegisterUserDto registerUserDto){
        User user = new User();
                user.setUserName(registerUserDto.getUserName());
                user.setPassword(registerUserDto.getPassword());

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto loginUserDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.getUserName(),
                        loginUserDto.getPassword()
                )
        );

        return userRepository.findByUserName(loginUserDto.getUserName())
                .orElseThrow();
    }
}
