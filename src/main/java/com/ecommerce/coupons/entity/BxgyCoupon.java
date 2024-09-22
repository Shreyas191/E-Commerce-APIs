package com.ecommerce.coupons.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@DiscriminatorValue("bxgy")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BxgyCoupon extends Coupon {

    private Integer repetitionLimit;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> buyProducts;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> getProducts;

    @Override
    public String getDetails() {
        return "Buy X Get Y deal with repetition limit of " + repetitionLimit;
    }

    // Getters and Setters
}

