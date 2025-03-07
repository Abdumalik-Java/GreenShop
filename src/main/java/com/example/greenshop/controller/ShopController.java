package com.example.greenshop.controller;

import com.example.greenshop.dto.ShopDto;
import com.example.greenshop.model.Result;
import com.example.greenshop.model.Shop;
import com.example.greenshop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ShopService shopService;

    @GetMapping
    public HttpEntity<?> readAll() {
        List<Shop> all = shopService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> readOne(@PathVariable UUID id) {
        Shop shop = shopService.findById(id);
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<?> create(@RequestBody ShopDto shopDto) {
        Result result = shopService.create(shopDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable UUID id, @RequestBody ShopDto shopDto) {
        Result update = shopService.update(shopDto, id);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        Result delete = shopService.delete(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}