package com.example.greenshop.controller;

import com.example.greenshop.dto.PriceRangeDto;
import com.example.greenshop.model.PriceRange;
import com.example.greenshop.model.Result;
import com.example.greenshop.service.PriceRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/priceRange")
public class PriceRangeController {

    @Autowired
    PriceRangeService priceRangeService;

    @GetMapping
    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN','USER')")
    public HttpEntity<?> readAll() {
        List<PriceRange> priceRanges = priceRangeService.readAll();
        return new ResponseEntity<>(priceRanges, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN','USER')")
    public HttpEntity<?> readOne(@PathVariable UUID id) {
        PriceRange priceRange = priceRangeService.readById(id);
        return new ResponseEntity<>(priceRange, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN')")
    public HttpEntity<?> create(@RequestBody PriceRangeDto priceRangeDto) {
        Result result = priceRangeService.create(priceRangeDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN')")
    public HttpEntity<?> update(@PathVariable UUID id, @RequestBody PriceRangeDto priceRangeDto) {
        Result update = priceRangeService.update(priceRangeDto, id);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN')")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        Result delete = priceRangeService.delete(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}
