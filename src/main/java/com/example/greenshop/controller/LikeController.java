package com.example.greenshop.controller;

import com.example.greenshop.dto.LikeDto;
import com.example.greenshop.model.Like;
import com.example.greenshop.model.Result;
import com.example.greenshop.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    LikeService likeService;

    @GetMapping
    public HttpEntity<?> readAll() {
        List<Like> likes = likeService.readLikes();
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> readOne(@PathVariable UUID id) {
        Like like = likeService.readLike(id);
        return new ResponseEntity<>(like, HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<?> create(@RequestBody LikeDto likeDto) {
        Result like = likeService.createLike(likeDto);
        return new ResponseEntity<>(like, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable UUID id, @RequestBody LikeDto likeDto) {
        Result like = likeService.updateLike(id, likeDto);
        return new ResponseEntity<>(like, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        Result like = likeService.deleteLike(id);
        return new ResponseEntity<>(like, HttpStatus.OK);
    }

}