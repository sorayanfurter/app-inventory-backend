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
    public UserDTO registerUser (UserRegisterDTO user){
        User u = new User();
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setEmail(user.getEmail());
        // Encode password
        u.setPassword(encoder.encode(user.getPassword()));
        // Set role to user
        u.setRole(roleRepo.findById(1).get());
        User created = repo.save(u);
        return new UserDTO(created.getId(),created.getName(),created.getSurname(),created.getEmail(),created.getRole().getName());


    }


    public UserDTO getUserById(String id){


        Optional<User> repoResponse = repo.findById(id);
        if(repoResponse.isPresent()){
            User u = repoResponse.get();
            return new UserDTO(u.getId(),u.getName(),u.getSurname(),u.getEmail(),u.getRole().getName());
        }else{
            return null;
        }





    }

    public List<UserDTO>  getAllUsers(){
        List<User> users = repo.findAll();
        List <UserDTO> usersDTO = new ArrayList<>();
        for(User u : users){
            usersDTO.add(new UserDTO(u.getId(),u.getName(),u.getSurname(),u.getEmail(),u.getRole().getName()));
        }
        return usersDTO;
    }


}
