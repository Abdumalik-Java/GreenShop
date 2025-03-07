package com.example.greenshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false, unique = true)
    private Long sku;
    @Column(nullable = false)
    private Integer productCount;
    @ManyToMany
    private List<Category> categoriesId;
    @OneToOne
    private Like likeId;
    @ManyToMany
    private List<Size> sizesId;
    @OneToOne
    private Tag tagId;

    @CreatedDate
    private LocalDateTime createdDateTime;

}