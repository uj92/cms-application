package com.example.sepm.sepm.Model;

import com.example.sepm.sepm.Model.data.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    
}