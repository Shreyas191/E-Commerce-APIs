package com.ecommerce.coupons.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartWiseDetailsDTO {

    private int threshold;
    private int discount;
}
