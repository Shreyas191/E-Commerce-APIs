package com.ecommerce.coupons.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class CartWrapperDTO {

    @JsonProperty("cart")
    private CartRequest cart;

    public CartWrapperDTO() {
    }

    @JsonCreator
    public CartWrapperDTO(@JsonProperty("cart") CartRequest cart) {
        this.cart = cart;
    }

    public CartRequest getCart() {
        return cart;
    }

    public void setCart(CartRequest cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "CartWrapper{" +
                "cart=" + cart +
                '}';
    }

    public static class CartRequest {

        @JsonProperty("items")
        private List<Item> items;

        public CartRequest() {
        }

        @JsonCreator
        public CartRequest(@JsonProperty("items") List<Item> items) {
            this.items = items;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public static class Item {
            @JsonProperty("product_id")
            private int productId;

            @JsonProperty("quantity")
            private int quantity;

            @JsonProperty("price")
            private double price;

            public Item() {
            }

            @JsonCreator
            public Item(@JsonProperty("product_id") int productId,
                        @JsonProperty("quantity") int quantity,
                        @JsonProperty("price") double price) {
                this.productId = productId;
                this.quantity = quantity;
                this.price = price;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            @Override
            public String toString() {
                return "Item{" +
                        "productId=" + productId +
                        ", quantity=" + quantity +
                        ", price=" + price +
                        '}';
            }
        }
    }
}
