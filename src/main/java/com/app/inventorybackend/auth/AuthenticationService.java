package com.app.inventorybackend.auth;

import com.app.inventorybackend.exceptions.EmailNotFoundException;
import com.app.inventorybackend.security.JwtService;
import com.app.inventorybackend.repository.UserRepository;
import com.app.inventorybackend.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository repository;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        if(request.getEmail() == null) {
            throw new EmailNotFoundException("Email not found");
        }

        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(request.getEmail());
        String jwtToken = jwtService.generateToken(userDetails);

        return  new AuthenticationResponse(jwtToken);
    }
}
