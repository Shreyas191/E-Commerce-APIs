package com.ecommerce.coupons.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductWiseDiscountDTO extends DiscountDTO {

    @JsonProperty("details")
    private ProductWiseDetails details;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductWiseDetails {

        @JsonProperty("product_id")
        private int productId;

        @JsonProperty("discount")
        private int discount;
    }
}
