package com.app.inventorybackend.service;

import com.app.inventorybackend.entity.User;
import com.app.inventorybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
public class UserService {
    @Autowired
    UserRepository repo;

    public void addUser(User u){
        repo.save(u);
    }
}
