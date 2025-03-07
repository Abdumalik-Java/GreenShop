package com.example.greenshop.service;

import com.example.greenshop.dto.ShoppingCardDto;
import com.example.greenshop.model.Product;
import com.example.greenshop.model.Result;
import com.example.greenshop.model.ShoppingCard;
import com.example.greenshop.model.Total;
import com.example.greenshop.repository.ProductRepo;
import com.example.greenshop.repository.ShoppingCardRepo;
import com.example.greenshop.repository.TotalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShoppingCardService {

    @Autowired
    ShoppingCardRepo repo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    TotalRepo totalRepo;

    public List<ShoppingCard> findAll() {
        return repo.findAll();
    }

    public ShoppingCard findById(UUID id) {
        return repo.findById(id).get();
    }

    public Result create(ShoppingCardDto dto) {
        ShoppingCard shoppingCard = new ShoppingCard();

        Optional<Product> byId = productRepo.findById(dto.getProductId());
        Product product = byId.get();
        shoppingCard.setProductId(product);

        Optional<Product> byId1 = productRepo.findById(dto.getProductPrice());
        Product product1 = byId1.get();
        shoppingCard.setProductPrice(product1);

        shoppingCard.setQuantity(dto.getQuantity());
        shoppingCard.setPrice(dto.getPrice());

        Optional<Total> byId2 = totalRepo.findById(dto.getTotalId());
        Total total = byId2.get();
        shoppingCard.setTotal(total);

        shoppingCard.setCoupon(dto.getCoupon());
        shoppingCard.setSubTotal(dto.getSubTotal());
        shoppingCard.setCouponDiscount(dto.getCouponDiscount());
        shoppingCard.setShipping(dto.getShipping());
        shoppingCard.setTotalCard(dto.getTotalCard());

        repo.save(shoppingCard);
        return new Result("Shopping card created successfully", true);
    }

    public Result update(ShoppingCardDto dto, UUID id) {
        Optional<ShoppingCard> byId = repo.findById(id);
        if (byId.isPresent()) {
            ShoppingCard shoppingCard = byId.get();

            Optional<Product> byIdorg = productRepo.findById(dto.getProductId());
            Product product = byIdorg.get();
            shoppingCard.setProductId(product);

            Optional<Product> byId1 = productRepo.findById(dto.getProductPrice());
            Product product1 = byId1.get();
            shoppingCard.setProductPrice(product1);

            shoppingCard.setQuantity(dto.getQuantity());
            shoppingCard.setPrice(dto.getPrice());

            Optional<Total> byId2 = totalRepo.findById(dto.getTotalId());
            Total total = byId2.get();
            shoppingCard.setTotal(total);

            shoppingCard.setCoupon(dto.getCoupon());
            shoppingCard.setSubTotal(dto.getSubTotal());
            shoppingCard.setCouponDiscount(dto.getCouponDiscount());
            shoppingCard.setShipping(dto.getShipping());
            shoppingCard.setTotalCard(dto.getTotalCard());

            repo.save(shoppingCard);
            return new Result("Shopping card updated successfully", true);
        }
        return new Result("Shopping card not found", false);
    }

    public Result delete(UUID id) {
        Optional<ShoppingCard> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.deleteById(id);
            return new Result("Shopping card deleted successfully", true);
        }
        return new Result("Shopping card not found", false);
    }

}