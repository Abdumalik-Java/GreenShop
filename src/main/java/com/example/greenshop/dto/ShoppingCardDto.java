package com.example.greenshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCardDto {

    private UUID productId;
    private UUID productPrice;

    private Integer quantity;
    private Double price;

    private UUID totalId;

    private String coupon;
    private Double subTotal;
    private String couponDiscount;
    private Double shipping;
    private Double totalCard;

}