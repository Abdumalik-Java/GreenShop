package com.example.greenshop.service;

import com.example.greenshop.dto.LikeDto;
import com.example.greenshop.model.Like;
import com.example.greenshop.model.Result;
import com.example.greenshop.repository.LikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LikeService {

    @Autowired
    LikeRepo repo;

    public List<Like> readLikes() {
        return repo.findAll();
    }

    public Like readLike(UUID id) {
        return repo.findById(id).get();
    }

    public Result createLike(LikeDto dto) {
        Like like = new Like();
        like.setName(dto.getName());
        like.setCount(dto.getCount());
        repo.save(like);
        return new Result("Like information created successfully", true);
    }

    public Result updateLike(UUID id, LikeDto dto) {
        Optional<Like> byId = repo.findById(id);
        if (byId.isPresent()) {
            Like like = byId.get();
            like.setName(dto.getName());
            like.setCount(dto.getCount());
            repo.save(like);
            return new Result("Like information updated successfully", true);
        }
        return new Result("Like information not found", false);
    }

    public Result deleteLike(UUID id) {
        Optional<Like> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.delete(byId.get());
            return new Result("Like information deleted successfully", true);
        }
        return new Result("Like information not found", false);
    }

}