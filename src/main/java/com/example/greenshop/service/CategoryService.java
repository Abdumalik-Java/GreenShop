package com.example.greenshop.service;

import com.example.greenshop.dto.CategoryDto;
import com.example.greenshop.model.Category;
import com.example.greenshop.model.Result;
import com.example.greenshop.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public List<Category> readAll() {
        return categoryRepo.findAll();
    }

    public Category readById(UUID id) {
        return categoryRepo.findById(id).get();
    }

    public Category readByName(String name) {
        return categoryRepo.findByName(name).get();
    }

    public Result create(CategoryDto categoryDto) {
        boolean b = categoryRepo.existsByName(categoryDto.getName());
        if (b) {
            return new Result("Category information is already exists", false);
        }
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setCategoryCount(categoryDto.getCategoryCount());
        categoryRepo.save(category);
        return new Result("Category information created successfully", true);
    }

    public Result update(CategoryDto categoryDto, UUID id) {
        Optional<Category> byId = categoryRepo.findById(id);
        if (byId.isPresent()) {
            Category category = byId.get();
            category.setName(categoryDto.getName());
            category.setCategoryCount(categoryDto.getCategoryCount());
            categoryRepo.save(category);
            return new Result("Category information updated successfully", true);
        }
        return new Result("Category information not found", false);
    }

    public Result delete(UUID id) {
        Optional<Category> byId = categoryRepo.findById(id);
        if (byId.isPresent()) {
            categoryRepo.delete(byId.get());
            return new Result("Category information deleted successfully", true);
        }
        return new Result("Category information not found", false);
    }

}