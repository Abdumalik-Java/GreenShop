package com.example.greenshop.service;

import com.example.greenshop.dto.ProductDto;
import com.example.greenshop.model.*;
import com.example.greenshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepo repo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    LikeRepo likeRepo;

    @Autowired
    SizeRepo sizeRepo;

    @Autowired
    TagRepo tagRepo;

    public List<Product> readAll() {
        return repo.findAll();
    }

    public Product readById(UUID id) {
        return repo.findById(id).get();
    }

    public Result create(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setSku(productDto.getSku());
        product.setProductCount(productDto.getProductCount());

        Optional<Category> byId = categoryRepo.findById(productDto.getCategoryId());
        Category category = byId.get();
        product.setCategoriesId((List<Category>) category);

        Optional<Like> byId1 = likeRepo.findById(productDto.getLikeId());
        Like like = byId1.get();
        product.setLikeId(like);

        Optional<Size> byId2 = sizeRepo.findById(productDto.getSizeId());
        Size size = byId2.get();
        product.setSizesId((List<Size>) size);

        Optional<Tag> byId3 = tagRepo.findById(productDto.getTagId());
        Tag tag = byId3.get();
        product.setTagId(tag);

        repo.save(product);
        return new Result("Product information created successfully", true);
    }

    public Result update(ProductDto productDto, UUID id) {
        Optional<Product> byId = repo.findById(id);
        if (byId.isPresent()) {
            Product product = byId.get();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setSku(productDto.getSku());
            product.setProductCount(productDto.getProductCount());

            Optional<Category> byId1 = categoryRepo.findById(productDto.getCategoryId());
            Category category = byId1.get();
            product.setCategoriesId((List<Category>) category);

            Optional<Like> byId2 = likeRepo.findById(productDto.getLikeId());
            Like like = byId2.get();
            product.setLikeId(like);

            Optional<Size> byId3 = sizeRepo.findById(productDto.getSizeId());
            Size size = byId3.get();
            product.setSizesId((List<Size>) size);

            Optional<Tag> byId4 = tagRepo.findById(productDto.getTagId());
            Tag tag = byId4.get();
            product.setTagId(tag);

            repo.save(product);
            return new Result("Product information updated successfully", true);
        }
        return new Result("Product information not found", false);
    }

    public Result delete(UUID id) {
        Optional<Product> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.delete(byId.get());
            return new Result("Product information deleted successfully", true);
        }
        return new Result("Product information not found", false);
    }

}