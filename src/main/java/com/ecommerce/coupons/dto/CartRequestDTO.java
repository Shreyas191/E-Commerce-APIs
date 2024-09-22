package com.ecommerce.coupons.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class CartRequestDTO {

    @JsonProperty("items")
    private List<Item> items;

    @JsonCreator
    public CartRequestDTO(@JsonProperty("items")List<Item> items) {
        this.items = items;
    }

    public CartRequestDTO() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CartRequest{" +
                "items=" + items +
                '}';
    }

    public static class Item {

        @JsonProperty("product_id")
        private int productId;

        public Item() {
        }

        public Item(int productId, int quantity, double price) {
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

        @JsonProperty("quantity")
        private int quantity;

        @JsonProperty("price")
        private double price;
    }
}
