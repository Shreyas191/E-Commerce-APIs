package com.ecommerce.coupons.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BxGyDetailsDTO {

    List<ProductRequestDTO> buyProducts;
    List<ProductRequestDTO> getBuyProducts;
    private int repetitionLimit;
}
