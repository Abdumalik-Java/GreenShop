package com.example.greenshop.controller;

import com.example.greenshop.dto.ShoppingCardDto;
import com.example.greenshop.model.Result;
import com.example.greenshop.model.ShoppingCard;
import com.example.greenshop.service.ShoppingCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shoppingCard")
public class ShoppingCardController {

    @Autowired
    ShoppingCardService shoppingCardService;

    @GetMapping
    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN','USER')")
    public HttpEntity<?> readAll() {
        List<ShoppingCard> all = shoppingCardService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN','USER')")
    public HttpEntity<?> readOne(@PathVariable UUID id) {
        ShoppingCard byId = shoppingCardService.findById(id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN')")
    public HttpEntity<?> create(@RequestBody ShoppingCardDto shoppingCardDto) {
        Result result = shoppingCardService.create(shoppingCardDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN','USER')")
    public HttpEntity<?> update(@PathVariable UUID id, @RequestBody ShoppingCardDto shoppingCardDto) {
        Result update = shoppingCardService.update(shoppingCardDto, id);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN','USER')")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        Result delete = shoppingCardService.delete(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}