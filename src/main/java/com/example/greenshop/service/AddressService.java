package com.example.greenshop.service;

import com.example.greenshop.dto.AddressDto;
import com.example.greenshop.model.Address;
import com.example.greenshop.model.Result;
import com.example.greenshop.repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    AddressRepo repo;

    public List<Address> getAllAddresses() {
        return repo.findAll();
    }

    public Address getAddressById(UUID id) {
        return repo.findById(id).get();
    }

    public Result createAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setCountry(addressDto.getCountry());
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setState(addressDto.getState());
        address.setZip(addressDto.getZip());
        repo.save(address);
        return new Result("Address information created successfully", true);
    }

    public Result updateAddress(UUID id, AddressDto addressDto) {
        boolean b = repo.existsByZip(addressDto.getZip());
        if (b) {
            return new Result("Address already exists", false);
        }
        Optional<Address> byId = repo.findById(id);
        if (byId.isPresent()) {
            Address address = byId.get();
            address.setCountry(addressDto.getCountry());
            address.setCity(addressDto.getCity());
            address.setStreet(addressDto.getStreet());
            address.setState(addressDto.getState());
            address.setZip(addressDto.getZip());
            repo.save(address);
            return new Result("Address information updated successfully", true);
        }
        return new Result("Address not found", false);
    }

    public Result deleteAddress(UUID id) {
        Optional<Address> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.delete(byId.get());
            return new Result("Address information deleted successfully", true);
        }
        return new Result("Address not found", false);
    }
}
