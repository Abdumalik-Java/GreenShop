package com.example.greenshop.repository;

import com.example.greenshop.model.PriceRange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PriceRangeRepo extends JpaRepository<PriceRange, UUID> {
}
