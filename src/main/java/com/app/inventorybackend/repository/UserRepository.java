package com.app.inventorybackend.repository;

import com.app.inventorybackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

}
