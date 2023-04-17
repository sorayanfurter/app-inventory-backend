package com.app.inventorybackend.model.mapper;

import com.app.inventorybackend.model.dto.UserDTO;
import com.app.inventorybackend.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {


    public UserMapper(){
    }

    public UserDTO mapToDTO(User user){
        return Mapper.map(user,UserDTO.class);
    }

    public User mapToEntity(UserDTO userDTO){
        return Mapper.map(userDTO,User.class);
    }

    public List<UserDTO> mapListToDTO(List<User> users){
        return Mapper.mapList(users,UserDTO.class);
    }



}
