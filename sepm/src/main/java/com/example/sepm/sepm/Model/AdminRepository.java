package com.example.sepm.sepm.Model;

import com.example.sepm.sepm.Model.data.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByUsername(String username);
}