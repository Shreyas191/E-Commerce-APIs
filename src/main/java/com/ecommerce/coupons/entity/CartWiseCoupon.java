package com.ecommerce.coupons.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@DiscriminatorValue("cart-wise")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartWiseCoupon extends Coupon {

    private Double threshold;
    private Double discountPercentage;

    @Override
    public String getDetails() {
        return "Cart-wise discount of " + discountPercentage + "% for cart total above " + threshold;
    }

    // Getters and Setters
}

