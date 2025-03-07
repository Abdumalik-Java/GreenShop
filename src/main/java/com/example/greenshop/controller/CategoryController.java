package com.example.greenshop.controller;

import com.example.greenshop.dto.CategoryDto;
import com.example.greenshop.model.Category;
import com.example.greenshop.model.Result;
import com.example.greenshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public HttpEntity<?> readAll() {
        List<Category> all = categoryService.readAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> readOne(@PathVariable UUID id) {
        Category category = categoryService.readById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public HttpEntity<?> readByName(@PathVariable String name) {
        Category category = categoryService.readByName(name);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<?> create(@RequestBody CategoryDto categoryDto) {
        Result result = categoryService.create(categoryDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable UUID id, @RequestBody CategoryDto categoryDto) {
        Result update = categoryService.update(categoryDto, id);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        Result delete = categoryService.delete(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}