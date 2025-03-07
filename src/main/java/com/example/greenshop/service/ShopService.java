package com.example.greenshop.service;

import com.example.greenshop.dto.ShopDto;
import com.example.greenshop.model.Category;
import com.example.greenshop.model.Result;
import com.example.greenshop.model.Shop;
import com.example.greenshop.repository.CategoryRepo;
import com.example.greenshop.repository.ShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShopService {

    @Autowired
    ShopRepo repo;

    @Autowired
    CategoryRepo categoryRepo;

    public List<Shop> findAll() {
        return repo.findAll();
    }

    public Shop findById(UUID id) {
        return repo.findById(id).get();
    }

    public Result create(ShopDto shopDto) {
        Shop shop = new Shop();
        Optional<Category> byId = categoryRepo.findById(shopDto.getCategoryId());
        Category category = byId.get();
        shop.setCategory((List<Category>) category);

        shop.setReviewCount(shopDto.getReviewCountDto());
        repo.save(shop);
        return new Result("Shop information created successfully", true);
    }

    public Result update(ShopDto shopDto, UUID id) {
        Optional<Shop> byId = repo.findById(id);
        if (byId.isPresent()) {
            Shop shop = byId.get();
            Optional<Category> byId1 = categoryRepo.findById(shopDto.getCategoryId());
            Category category = byId1.get();
            shop.setCategory((List<Category>) category);

            shop.setReviewCount(shopDto.getReviewCountDto());
            repo.save(shop);
            return new Result("Shop information updated successfully", true);
        }
        return new Result("Shop information not found", false);
    }

    public Result delete(UUID id) {
        Optional<Shop> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.delete(byId.get());
            return new Result("Shop information deleted successfully", true);
        }
        return new Result("Shop information not found", false);
    }

}