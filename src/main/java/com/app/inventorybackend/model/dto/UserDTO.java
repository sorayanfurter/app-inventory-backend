package com.app.inventorybackend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserDTO implements Serializable {

    public UserDTO() {
    }

    String id;
    String name;
    String surname;
    String email;
    RoleDTO role;


}
