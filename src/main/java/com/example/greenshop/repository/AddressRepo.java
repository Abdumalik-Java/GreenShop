package com.example.greenshop.repository;

import com.example.greenshop.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepo extends JpaRepository<Address, UUID> {
    boolean existsByZip(String zip);
}
