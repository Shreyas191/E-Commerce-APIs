package com.ecommerce.coupons.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductWiseDetailsDTO {

    private int productId;
    private int discount;
}
