package com.app.inventorybackend.service;

import com.app.inventorybackend.model.dto.UserDTO;
import com.app.inventorybackend.model.dto.UserRegisterDTO;
import com.app.inventorybackend.model.entity.User;
import com.app.inventorybackend.repository.RoleRepository;
import com.app.inventorybackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repo;
    @Autowired
    RoleRepository roleRepo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10,new SecureRandom());


    @Transactional
    public User registerUser (UserRegisterDTO user){
        User u = new User();
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setEmail(user.getEmail());
        // Encode password
        u.setPassword(encoder.encode(user.getPassword()));
        // Set role to user
        u.setRole(roleRepo.findById(1).get());
        User created = repo.save(u);
        return created;


    }


    public User getUserById(String id){
        Optional<User> repoResponse = repo.findById(id);
        if(repoResponse.isPresent()){
            User u = repoResponse.get();
            return u;
        }else {
            return null;
        }
    }

    public List<User>  getAllUsers(){
        List<User> users = repo.findAll();
        return users;
    }


}
