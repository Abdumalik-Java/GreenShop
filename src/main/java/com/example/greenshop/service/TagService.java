package com.example.greenshop.service;

import com.example.greenshop.dto.TagDto;
import com.example.greenshop.model.Result;
import com.example.greenshop.model.Tag;
import com.example.greenshop.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TagService {

    @Autowired
    TagRepo repo;

    public List<Tag> getTags() {
        return repo.findAll();
    }

    public Tag getTagById(UUID id) {
        return repo.findById(id).get();
    }

    public Result createTag(TagDto tagDto) {
        Tag tag = new Tag();
        tag.setName(tagDto.getName());
        tag.setCount(tagDto.getCount());
        repo.save(tag);
        return new Result("Tag information created successfully", true);
    }

    public Result updateTag(UUID id, TagDto tagDto) {
        Optional<Tag> byId = repo.findById(id);
        if (byId.isPresent()) {
            Tag tag = byId.get();
            tag.setName(tagDto.getName());
            tag.setCount(tagDto.getCount());
            repo.save(tag);
            return new Result("Tag information updated successfully", true);
        }
        return new Result("Tag information not found", false);
    }

    public Result deleteTag(UUID id) {
        Optional<Tag> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.delete(byId.get());
            return new Result("Tag information deleted successfully", true);
        }
        return new Result("Tag information not found", false);
    }

}