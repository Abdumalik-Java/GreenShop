package com.example.greenshop.controller;

import com.example.greenshop.dto.SizeDto;
import com.example.greenshop.model.Result;
import com.example.greenshop.model.Size;
import com.example.greenshop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/size")
public class SizeController {

    @Autowired
    SizeService sizeService;

    @GetMapping
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN','/USER')")
    public HttpEntity<?> readAll() {
        List<Size> all = sizeService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN','/USER')")
    public HttpEntity<?> readOne(@PathVariable UUID id) {
        Size byId = sizeService.findById(id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN','/USER')")
    public HttpEntity<?> create(@RequestBody SizeDto sizeDto) {
        Result result = sizeService.create(sizeDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN','/USER')")
    public HttpEntity<?> update(@PathVariable UUID id, @RequestBody SizeDto dto) {
        Result update = sizeService.update(id, dto);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN')")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        Result delete = sizeService.delete(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}