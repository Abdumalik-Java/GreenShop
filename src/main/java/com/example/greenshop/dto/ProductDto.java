package com.example.greenshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String name;
    private String description;
    private Double price;
    private Long sku;
    private Integer productCount;

    private UUID categoryId;
    private UUID likeId;
    private UUID sizeId;
    private UUID tagId;

}