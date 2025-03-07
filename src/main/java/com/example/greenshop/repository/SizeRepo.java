package com.example.greenshop.repository;

import com.example.greenshop.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SizeRepo extends JpaRepository<Size, UUID> {
}
