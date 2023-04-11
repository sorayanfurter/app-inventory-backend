package com.app.inventorybackend.service;



import com.app.inventorybackend.exceptions.EmailNotFoundException;
import com.app.inventorybackend.model.entity.User;
import com.app.inventorybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)  {
        Optional<User> userResp = userRepository.findUserByEmail(email);
        if (userResp.isEmpty() || userResp == null) {
            throw new EmailNotFoundException("Email not found");
        }
            User user = userResp.get();
            return new org.springframework.security.core.userdetails.User(email, user.getPassword(), new ArrayList<>());
        }


}
