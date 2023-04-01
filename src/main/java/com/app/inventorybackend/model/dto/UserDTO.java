package com.app.inventorybackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserDTO implements Serializable {

    String id;
    String name;
    String surname;
    String email;
    String role;

}
