package com.example.greenshop.service;

import com.example.greenshop.dto.PriceRangeDto;
import com.example.greenshop.model.PriceRange;
import com.example.greenshop.model.Result;
import com.example.greenshop.repository.PriceRangeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PriceRangeService {

    @Autowired
    PriceRangeRepo repo;

    public List<PriceRange> readAll() {
        return repo.findAll();
    }

    public PriceRange readById(UUID id) {
        return repo.findById(id).get();
    }

    public Result create(PriceRangeDto priceRangeDto) {
        PriceRange priceRange = new PriceRange();
        priceRange.setName(priceRangeDto.getName());
        priceRange.setUsdCode(priceRangeDto.getUsdCode());
        repo.save(priceRange);
        return new Result("Price range created successfully", true);
    }

    public Result update(PriceRangeDto priceRangeDto, UUID id) {
        Optional<PriceRange> byId = repo.findById(id);
        if (byId.isPresent()) {
            PriceRange priceRange = byId.get();
            priceRange.setName(priceRangeDto.getName());
            priceRange.setUsdCode(priceRangeDto.getUsdCode());
            repo.save(priceRange);
            return new Result("Price range updated successfully", true);
        }
        return new Result("Price range not found", false);
    }

    public Result delete(UUID id) {
        Optional<PriceRange> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.delete(byId.get());
            return new Result("Price range deleted successfully", true);
        }
        return new Result("Price range not found", false);
    }

}