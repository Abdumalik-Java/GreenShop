package com.example.greenshop.service;

import com.example.greenshop.dto.PaymentDto;
import com.example.greenshop.model.Payment;
import com.example.greenshop.model.Result;
import com.example.greenshop.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    PaymentRepo repo;

    public List<Payment> getPayments() {
        return repo.findAll();
    }

    public Payment getPayment(UUID id) {
        return repo.findById(id).get();
    }

    public Result create(PaymentDto dto) {
        Payment payment = new Payment();
        payment.setName(dto.getName());
        payment.setCvvNumber(dto.getCvvNumber());
        payment.setExpireDate(dto.getExpireDate());
        repo.save(payment);
        return new Result("Payment system created successfully", true);
    }

    public Result update(PaymentDto dto, UUID id) {
        Optional<Payment> byId = repo.findById(id);
        if (byId.isPresent()) {
            Payment payment = byId.get();
            payment.setName(dto.getName());
            payment.setCvvNumber(dto.getCvvNumber());
            payment.setExpireDate(dto.getExpireDate());
            repo.save(payment);
            return new Result("Payment system updated successfully", true);
        }
        return new Result("Payment system not found", false);
    }

    public Result delete(UUID id) {
        Optional<Payment> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.deleteById(id);
            return new Result("Payment system deleted successfully", true);
        }
        return new Result("Payment system not found", false);
    }

}