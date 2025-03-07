package com.example.greenshop.repository;

import com.example.greenshop.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShopRepo extends JpaRepository<Shop, UUID> {
}