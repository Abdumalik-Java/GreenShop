package com.example.greenshop.repository;

import com.example.greenshop.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoginRepo extends JpaRepository<Login, UUID> {

    boolean existsByEmail(String email);

}