package com.app.inventorybackend.auth;

import com.app.inventorybackend.model.dto.UserDTO;
import com.app.inventorybackend.model.dto.UserRegisterDTO;
import com.app.inventorybackend.model.mapper.UserMapper;
import com.app.inventorybackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationService authservice;

    @Autowired
    private UserService service;

    private UserMapper mapper = new UserMapper();

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authservice.authenticate(request));
    }


    /**
     * Este método realiza el registro de un usuario
     * @param user UserRegisterDTO
     * @return UserDTO
     */

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegisterDTO user){
        UserDTO us = mapper.mapToDTO(service.registerUser(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(us);

    }



}
