package com.ecommerce.coupons.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequestDTO {

    private Long productId;  // ID of the product
    private Integer quantity;  // Quantity of the product
}

