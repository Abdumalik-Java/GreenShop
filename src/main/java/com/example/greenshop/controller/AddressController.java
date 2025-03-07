package com.example.greenshop.controller;

import com.example.greenshop.dto.AddressDto;
import com.example.greenshop.model.Address;
import com.example.greenshop.model.Result;
import com.example.greenshop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public HttpEntity<?> readAll() {
        List<Address> allAddresses = addressService.getAllAddresses();
        return new ResponseEntity<>(allAddresses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> readOne(@PathVariable UUID id) {
        Address address = addressService.getAddressById(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<?> create(@RequestBody AddressDto addressDto) {
        Result address = addressService.createAddress(addressDto);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable UUID id, @RequestBody AddressDto addressDto) {
        Result address = addressService.updateAddress(id, addressDto);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        Result address = addressService.deleteAddress(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

}