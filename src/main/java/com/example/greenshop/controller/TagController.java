package com.example.greenshop.controller;

import com.example.greenshop.dto.TagDto;
import com.example.greenshop.model.Result;
import com.example.greenshop.model.Tag;
import com.example.greenshop.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN','/USER')")
    public HttpEntity<?> readAll() {
        List<Tag> tags = tagService.getTags();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN','/USER')")
    public HttpEntity<?> readOne(@PathVariable UUID id) {
        Tag tag = tagService.getTagById(id);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN','/USER')")
    public HttpEntity<?> create(@RequestBody TagDto tagDto) {
        Result tag = tagService.createTag(tagDto);
        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN','/USER')")
    public HttpEntity<?> update(@PathVariable UUID id, @RequestBody TagDto tagDto) {
        Result tag = tagService.updateTag(id, tagDto);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('/SUPERADMIN','/ADMIN','/USER')")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        Result tag = tagService.deleteTag(id);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

}