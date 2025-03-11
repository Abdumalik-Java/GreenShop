package com.example.greenshop.controller;

import com.example.greenshop.dto.UserDto;
import com.example.greenshop.model.Result;
import com.example.greenshop.model.User;
import com.example.greenshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN','/USER')")
    public HttpEntity<?> readAll() {
        List<User> allUsers = service.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN','/USER')")
    public HttpEntity<?> readById(@PathVariable UUID id) {
        User byId = service.getById(id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN','/USER')")
    public HttpEntity<?> create(@RequestBody UserDto userDto) {
        Result user = service.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN','/USER')")
    public HttpEntity<?> update(@PathVariable UUID id, @RequestBody UserDto userDto) {
        Result result = service.updateUser(userDto, id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN','/USER')")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        Result result = service.deleteUser(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}