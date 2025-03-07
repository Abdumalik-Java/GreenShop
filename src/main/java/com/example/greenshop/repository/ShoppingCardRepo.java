package com.example.greenshop.repository;

import com.example.greenshop.model.ShoppingCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShoppingCardRepo extends JpaRepository<ShoppingCard, UUID> {
}
