package com.example.offer_management_be.service;

import com.example.offer_management_be.dto.LoginUserDto;
import com.example.offer_management_be.dto.RegisterUserDto;
import com.example.offer_management_be.exception.UserNotFoundException;
import com.example.offer_management_be.models.User;
import com.example.offer_management_be.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository  userRepository;
    private final PasswordEncoder passwordEncoder;



    private final AuthenticationManager authenticationManager;

    private  Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User signUp(RegisterUserDto registerUserDto){
        User user = new User();
        user.setUserName(registerUserDto.getUserName());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));

        Optional<User> exitsUser = userRepository.findByUserName(registerUserDto.getUserName());
            if(exitsUser.isPresent()){
                throw new RuntimeException("User existed");
            }
            else {
                return userRepository.save(user);
            }
    }

    public User authenticate(LoginUserDto loginUserDto) {
        logger.info(loginUserDto.getUserName());
        logger.info(loginUserDto.getPassword());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.getUserName(),
                        loginUserDto.getPassword()
                )
        );

        return userRepository.findByUserName(loginUserDto.getUserName())
                .orElseThrow(() -> new UserNotFoundException(loginUserDto.getUserName()));
    }
}
