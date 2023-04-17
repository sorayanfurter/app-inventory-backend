package com.app.inventorybackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RoleDTO implements Serializable {

    public RoleDTO() {
    }

    String id;
    String name;
}
