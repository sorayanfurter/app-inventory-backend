package com.app.inventorybackend.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.io.Serializable;


@Data
@AllArgsConstructor
public class UserRegisterDTO implements Serializable {


    @NotNull
    String name;
    @NotNull
    String surname;
    @NotNull
    String password;
    @NotNull
    String email;


}
