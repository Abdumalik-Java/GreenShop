package com.example.greenshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShoppingCard {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    private Product productId;
    @OneToOne
    private Product productPrice;

    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Double price;

    @OneToOne
    private Total total;

    @Column(nullable = false)
    private String coupon;
    @Column(nullable = false)
    private Double subTotal;
    @Column(nullable = false)
    private String couponDiscount;
    @Column(nullable = false)
    private Double shipping;
    @Column(nullable = false)
    private Double totalCard;

}