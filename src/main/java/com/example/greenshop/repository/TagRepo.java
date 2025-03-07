package com.example.greenshop.repository;

import com.example.greenshop.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TagRepo extends JpaRepository<Tag, UUID> {
}
