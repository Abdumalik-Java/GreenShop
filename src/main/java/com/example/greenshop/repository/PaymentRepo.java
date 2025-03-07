package com.example.greenshop.repository;

import com.example.greenshop.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepo extends JpaRepository<Payment, UUID> {
    boolean existsByCvvNumber(String cvvNumber);
}
