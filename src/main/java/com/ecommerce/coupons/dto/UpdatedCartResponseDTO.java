package com.ecommerce.coupons.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedCartResponseDTO {
    private UpdatedCart updatedCart;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdatedCart {
        private List<CartItem> items;
        private double totalPrice;
        private double totalDiscount;
        private double finalPrice;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class CartItem {
            private int productId;
            private int quantity;
            private double price;
            private double totalDiscount;
        }
    }

}
