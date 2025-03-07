package com.example.greenshop.service;

import com.example.greenshop.dto.TotalDto;
import com.example.greenshop.model.Result;
import com.example.greenshop.model.Total;
import com.example.greenshop.repository.TotalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TotalService {

    @Autowired
    TotalRepo repo;

    public List<Total> getTotals() {
        return repo.findAll();
    }

    public Total getById(UUID id) {
        return repo.findById(id).get();
    }

    public Result create(TotalDto dto) {
        Total total = new Total();
        total.setNumber(dto.getNumber());
        total.setPrice(dto.getPrice());
        repo.save(total);
        return new Result("Order information created successfully", true);
    }

    public Result update(TotalDto dto, UUID id) {
        Optional<Total> byId = repo.findById(id);
        if (byId.isPresent()) {
            Total total = byId.get();
            total.setNumber(dto.getNumber());
            total.setPrice(dto.getPrice());
            repo.save(total);
            return new Result("Order information updated successfully", true);
        }
        return new Result("Order information not found", false);
    }

    public Result delete(UUID id) {
        Optional<Total> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.delete(byId.get());
            return new Result("Order information deleted successfully", true);
        }
        return new Result("Order information not found", false);
    }

}