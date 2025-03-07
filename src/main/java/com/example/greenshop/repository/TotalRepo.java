package com.example.greenshop.repository;

import com.example.greenshop.model.Total;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TotalRepo extends JpaRepository<Total, UUID> {
}
