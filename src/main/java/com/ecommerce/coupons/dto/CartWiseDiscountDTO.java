package com.ecommerce.coupons.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CartWiseDiscountDTO extends DiscountDTO {

    @JsonProperty("details")
    private Details details;

    @JsonCreator
    public CartWiseDiscountDTO(@JsonProperty("details") Details details){
        this.details = details;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Details {
        private int threshold;
        private int discount;
    }
}
