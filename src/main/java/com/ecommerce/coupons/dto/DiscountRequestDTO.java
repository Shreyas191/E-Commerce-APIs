package com.ecommerce.coupons.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiscountRequestDTO {

    @JsonProperty("type")
    private String type;  // cart-wise, bxgy, etc.

    //private CartWiseDetails cartWiseDetails;
    @JsonProperty("details")
    private BxGyDetailsDTO bxGyDetailsDTO;
}

