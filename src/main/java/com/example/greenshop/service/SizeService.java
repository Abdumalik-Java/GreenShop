package com.example.greenshop.service;

import com.example.greenshop.dto.SizeDto;
import com.example.greenshop.model.Result;
import com.example.greenshop.model.Size;
import com.example.greenshop.repository.SizeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SizeService {

    @Autowired
    SizeRepo repo;

    public List<Size> findAll() {
        return repo.findAll();
    }

    public Size findById(UUID id) {
        return repo.findById(id).get();
    }

    public Result create(SizeDto dto) {
        Size size = new Size();
        size.setName(dto.getName());
        repo.save(size);
        return new Result("Size information created successfully", true);
    }

    public Result update(UUID id, SizeDto dto) {
        Optional<Size> byId = repo.findById(id);
        if (byId.isPresent()) {
            Size size = byId.get();
            size.setName(dto.getName());
            repo.save(size);
            return new Result("Size information updated successfully", true);
        }
        return new Result("Size information not found", false);
    }

    public Result delete(UUID id) {
        Optional<Size> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.delete(byId.get());
            return new Result("Size information deleted successfully", true);
        }
        return new Result("Size information not found", false);
    }

}