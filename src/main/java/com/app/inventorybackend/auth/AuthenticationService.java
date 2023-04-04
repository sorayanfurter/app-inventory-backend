package com.app.inventorybackend.auth;

import com.app.inventorybackend.security.JwtService;
import com.app.inventorybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


public class AuthenticationService {


    @Autowired
    UserRepository repository;

    @Autowired
    UserDetails userDetails;


    @Autowired
    JwtService jwtService;


    @Autowired
    AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(userDetails);


        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
