package com.ecommerce.coupons.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BxGyDiscountDTO extends DiscountDTO {

    @JsonProperty("details")
    private BxGyDetails details;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BxGyDetails {

        @JsonProperty("buy_products")
        private List<Product> buyProducts;

        @JsonProperty("get_products")
        private List<Product> getProducts;

        @JsonProperty("repition_limit")
        private int repitionLimit;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Product {

            @JsonProperty("product_id")
            private Long productId;

            @JsonProperty("quantity")
            private int quantity;
        }
    }
}
