package com.example.greenshop.controller;

import com.example.greenshop.dto.PaymentDto;
import com.example.greenshop.model.Payment;
import com.example.greenshop.model.Result;
import com.example.greenshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping
    public HttpEntity<?> readAll() {
        List<Payment> payments = paymentService.getPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> readOne(@PathVariable UUID id) {
        Payment payment = paymentService.getPayment(id);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<?> create(@RequestBody PaymentDto paymentDto) {
        Result result = paymentService.create(paymentDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable UUID id, @RequestBody PaymentDto paymentDto) {
        Result result = paymentService.update(paymentDto, id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        Result result = paymentService.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}