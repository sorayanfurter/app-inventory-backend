package com.app.inventorybackend.model.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;
    @Column(unique = true)
    String name;
}

