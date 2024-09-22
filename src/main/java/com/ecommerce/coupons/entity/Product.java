package com.ecommerce.coupons.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private Integer quantity;

    // Getters and Setters

//    public Product(Long productId, Integer quantity) {
//        this.productId = productId;
//        this.quantity = quantity;
//    }
//
//    public Product() {
//    }
}

