package com.example.greenshop.controller;

import com.example.greenshop.dto.ProductDto;
import com.example.greenshop.model.Product;
import com.example.greenshop.model.Result;
import com.example.greenshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public HttpEntity<?> readAll() {
        List<Product> products = productService.readAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> readById(@PathVariable UUID id) {
        Product product = productService.readById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<?> create(@RequestBody ProductDto productDto) {
        Result result = productService.create(productDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable UUID id, @RequestBody ProductDto productDto) {
        Result update = productService.update(productDto, id);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        Result delete = productService.delete(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}