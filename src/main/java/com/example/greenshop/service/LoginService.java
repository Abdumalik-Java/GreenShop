package com.example.greenshop.service;

import com.example.greenshop.dto.LoginDto;
import com.example.greenshop.model.Login;
import com.example.greenshop.model.Result;
import com.example.greenshop.repository.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    LoginRepo loginRepo;

    public List<Login> findAll() {
        return loginRepo.findAll();
    }

    public Login findById(UUID id) {
        return loginRepo.findById(id).get();
    }

    public Result create(LoginDto loginDto) {
        Login login = new Login();
        login.setName(loginDto.getName());
        login.setEmail(loginDto.getEmail());
        login.setPassword(loginDto.getPassword());
        login.setConfirmPassword(loginDto.getConfirmPassword());
        loginRepo.save(login);
        return new Result("Login information created successfully", true);
    }

    public Result update(LoginDto loginDto, UUID id) {
        Optional<Login> byId = loginRepo.findById(id);
        if (byId.isPresent()) {
            Login login = byId.get();
            login.setName(loginDto.getName());
            login.setEmail(loginDto.getEmail());
            login.setPassword(loginDto.getPassword());
            login.setConfirmPassword(loginDto.getConfirmPassword());
            loginRepo.save(login);
            return new Result("Login information updated successfully", true);
        }
        return new Result("Login information not found", false);
    }

    public Result delete(UUID id, LoginDto loginDto) {
        Optional<Login> byId = loginRepo.findById(id);
        if (byId.isPresent()) {
            loginRepo.existsByEmail(loginDto.getEmail());
            loginRepo.delete(byId.get());
            return new Result("Login information deleted successfully", true);
        }
        return new Result("Login information not found", false);
    }

}