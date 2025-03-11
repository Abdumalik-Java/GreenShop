package com.example.greenshop.controller;

import com.example.greenshop.dto.OrderDto;
import com.example.greenshop.model.Order;
import com.example.greenshop.model.Result;
import com.example.greenshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN','/USER')")
    public HttpEntity<?> readAll() {
        List<Order> allOrders = service.getAllOrders();
        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN','/USER')")
    public HttpEntity<?> readOne(@PathVariable UUID id) {
        Order order = service.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN')")
    public HttpEntity<?> create(@RequestBody OrderDto orderDto) {
        Result result = service.create(orderDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN')")
    public HttpEntity<?> update(@PathVariable UUID id, @RequestBody OrderDto orderDto) {
        Result update = service.update(orderDto, id);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN')")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        Result delete = service.delete(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}