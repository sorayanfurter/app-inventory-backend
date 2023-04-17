package com.app.inventorybackend.controller;



import com.app.inventorybackend.model.dto.ResponseDTO;
import com.app.inventorybackend.model.dto.UserDTO;
import com.app.inventorybackend.model.dto.UserRegisterDTO;
import com.app.inventorybackend.model.entity.User;
import com.app.inventorybackend.model.mapper.UserMapper;
import com.app.inventorybackend.service.UserService;
import jakarta.annotation.security.PermitAll;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@PermitAll()
public class UserController {

    @Autowired
    private UserService service;

    private UserMapper mapper = new UserMapper();



    /**
     * Este metodo obtiene un usuario por su id
     * @param id String
     * @return UserDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable String id){

        User us = service.getUserById(id);
        if(us == null){
            ResponseDTO response = new ResponseDTO("No se encontro el usuario");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(mapper.mapToDTO(us));
    }

    /**
     * Este metodo obtiene todos los usuarios
     * @return List<UserDTO>
     */
    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        System.out.println(mapper.mapListToDTO(service.getAllUsers()));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.mapListToDTO(service.getAllUsers()));
    }

}

