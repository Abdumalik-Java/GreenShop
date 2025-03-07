package com.example.greenshop.service;

import com.example.greenshop.dto.UserDto;
import com.example.greenshop.model.Address;
import com.example.greenshop.model.Result;
import com.example.greenshop.model.User;
import com.example.greenshop.repository.AddressRepo;
import com.example.greenshop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AddressRepo addressRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getById(UUID id) {
        return userRepo.findById(id).get();
    }

    public Result createUser(UserDto userDto) {
        boolean b = userRepo.existsByEmail(userDto.getEmail());
        if (b) {
            return new Result("User information is already exist",false);
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        user.setOrderNote(userDto.getOrderNote());

        Optional<Address> byId = addressRepo.findById(userDto.getAddressId());
        Address address = byId.get();
        user.setAddressId(address);

        userRepo.save(user);
        return new Result("User created successfully", true);
    }

    public Result updateUser(UserDto userDto, UUID id) {
        Optional<User> byId = userRepo.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setPassword(userDto.getPassword());
            user.setOrderNote(userDto.getOrderNote());

            Optional<Address> byId1 = addressRepo.findById(userDto.getAddressId());
            Address address = byId1.get();
            user.setAddressId(address);

            userRepo.save(user);
            return new Result("User updated successfully", true);
        }
        return new Result("User not found", false);
    }

    public Result deleteUser(UUID id) {
        Optional<User> byId = userRepo.findById(id);
        if (byId.isPresent()) {
            userRepo.delete(byId.get());
            return new Result("User deleted successfully", true);
        }
        return new Result("User not found", false);
    }

}