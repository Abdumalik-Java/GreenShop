package com.example.greenshop.repository;

import com.example.greenshop.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LikeRepo extends JpaRepository<Like, UUID> {
}
