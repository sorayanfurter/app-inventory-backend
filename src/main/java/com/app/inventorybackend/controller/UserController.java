package com.app.inventorybackend.controller;



import com.app.inventorybackend.model.dto.UserDTO;
import com.app.inventorybackend.model.dto.UserRegisterDTO;
import com.app.inventorybackend.model.entity.User;
import com.app.inventorybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;


    /**
     * Este método realiza el registro de un usuario
     * @param user UserRegisterDTO
     * @return UserDTO
     */

    @PostMapping("/")

    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegisterDTO user){
        UserDTO u = service.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }


    /**
     * Este metodo obtiene un usuario por su id
     * @param id String
     * @return UserDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id){
        UserDTO u = service.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }

    /**
     * Este metodo obtiene todos los usuarios
     * @return List<UserDTO>
     */
    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllUsers());
    }

}

