package com.example.greenshop.service;

import com.example.greenshop.dto.OrderDto;
import com.example.greenshop.model.Order;
import com.example.greenshop.model.Result;
import com.example.greenshop.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    OrderRepo repo;

    public List<Order> getAllOrders() {
        return repo.findAll();
    }

    public Order getOrderById(UUID id) {
        return repo.findById(id).get();
    }

    public Result create(OrderDto dto) {
        Order order = new Order();
        order.setOrderNumber(dto.getOrderNumber());
        order.setPaymentMethod(dto.getPaymentMethod());
        repo.save(order);
        return new Result("Order information created successfully", true);
    }

    public Result update(OrderDto dto, UUID id) {
        Optional<Order> byId = repo.findById(id);
        if (byId.isPresent()) {
            Order order = byId.get();
            order.setOrderNumber(dto.getOrderNumber());
            order.setPaymentMethod(dto.getPaymentMethod());
            repo.save(order);
            return new Result("Order information updated successfully", true);
        }
        return new Result("Order information not found", false);
    }

    public Result delete(UUID id) {
        Optional<Order> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.deleteById(id);
            return new Result("Order information deleted successfully", true);
        }
        return new Result("Order information not found", false);
    }
}
