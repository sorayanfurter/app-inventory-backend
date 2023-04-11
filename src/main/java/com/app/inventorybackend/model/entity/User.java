package com.app.inventorybackend.model.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


import java.io.*;
@Entity
@Data
public class User implements Serializable{

    @Id
    @GenericGenerator(name= "uuid", strategy= "uuid2")
    @GeneratedValue(generator= "uuid")
    @Column(nullable = false, length = 36)
    String id;
    
    @Column(nullable = false, length = 50)
    String name;


    @Column(nullable = false, length = 50)
    String surname;


    @Column(nullable = false)
    String password;


    @Column(nullable = false)
    String email;


    @ManyToOne
    Role role;


}

