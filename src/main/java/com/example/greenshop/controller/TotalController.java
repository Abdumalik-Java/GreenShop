package com.example.greenshop.controller;

import com.example.greenshop.dto.TotalDto;
import com.example.greenshop.model.Result;
import com.example.greenshop.model.Total;
import com.example.greenshop.service.TotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/total")
public class TotalController {

    @Autowired
    TotalService service;

    @GetMapping
    public HttpEntity<?> readAll() {
        List<Total> totals = service.getTotals();
        return new ResponseEntity<>(totals, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> readById(UUID id) {
        Total byId = service.getById(id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<?> create(@RequestBody TotalDto totalDto) {
        Result result = service.create(totalDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable UUID id, @RequestBody TotalDto totalDto) {
        Result update = service.update(totalDto, id);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        Result delete = service.delete(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}