package com.ecommerce.coupons.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {
    private int productId;
    private int quantity;
    private double price;
}
