package com.ecommerce.coupons.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, // Use the 'type' field to determine the class
        include = JsonTypeInfo.As.PROPERTY, // Include 'type' as a property
        property = "type", // Field in JSON that will indicate the type
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CartWiseDiscountDTO.class, name = "cart-wise"),
        @JsonSubTypes.Type(value = ProductWiseDiscountDTO.class, name = "product-wise"),
        @JsonSubTypes.Type(value = BxGyDiscountDTO.class, name = "bxgy")
})
public abstract class DiscountDTO {

    @JsonProperty("type")
    private String type;
}
