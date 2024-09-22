package com.ecommerce.coupons.service;

import com.ecommerce.coupons.entity.*;
import com.ecommerce.coupons.exception.CouponNotFoundException;
import com.ecommerce.coupons.exception.InvalidInputException;
import com.ecommerce.coupons.dto.CartWrapperDTO;
import com.ecommerce.coupons.dto.CouponResponseDTO;
import com.ecommerce.coupons.dto.UpdatedCartResponseDTO;
import com.ecommerce.coupons.repository.BxGyDiscountRepository;
import com.ecommerce.coupons.repository.CartWiseDiscountRepository;
import com.ecommerce.coupons.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicableCouponServiceImpl implements ApplicableCouponService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private CartWiseDiscountRepository cartWiseDiscountRepository;

    @Autowired
    private BxGyDiscountRepository bxGyDiscountRepository;

    @Override
    public List<CouponResponseDTO.ApplicableCoupon> findApplicableCoupons(CartWrapperDTO cartWrapperDTO) {
        if (cartWrapperDTO == null || cartWrapperDTO.getCart() == null || cartWrapperDTO.getCart().getItems().isEmpty()) {
            throw new InvalidInputException("Cart is empty or invalid.");
        }

        List<CouponResponseDTO.ApplicableCoupon> applicableCoupons = new ArrayList<>();
        List<CartWrapperDTO.CartRequest.Item> items = cartWrapperDTO.getCart().getItems();
        List<Discount> discounts = discountRepository.findAll();

        for (Discount discount : discounts) {
            if ("cart-wise".equals(discount.getType())) {
                List<CartWiseDiscount> cartWiseDiscounts = cartWiseDiscountRepository.findByDiscount(discount);
                for (CartWiseDiscount cartWiseDiscount : cartWiseDiscounts) {
                    if (cartWiseDiscount.getThreshold() <= calculateCartTotal(items)) {
                        applicableCoupons.add(new CouponResponseDTO.ApplicableCoupon(discount.getId(), discount.getType(), cartWiseDiscount.getDiscountValue()));
                    }
                }
            } else if ("bxgy".equals(discount.getType())) {
                List<BxGyDiscount> bxGyDiscounts = bxGyDiscountRepository.findByDiscount(discount);
                for (BxGyDiscount bxGyDiscount : bxGyDiscounts) {
                    if (isBxGyApplicable(items, bxGyDiscount)) {
                        double discountValue = calculateBxGyDiscount(items, bxGyDiscount);
                        applicableCoupons.add(new CouponResponseDTO.ApplicableCoupon(discount.getId(), discount.getType(), discountValue));
                    }
                }
            }
        }

        return applicableCoupons;
    }

    private double calculateCartTotal(List<CartWrapperDTO.CartRequest.Item> items) {
        return items.stream()
                .mapToDouble(item -> item.getQuantity() * item.getPrice())
                .sum();
    }

    private boolean isBxGyApplicable(List<CartWrapperDTO.CartRequest.Item> items, BxGyDiscount bxGyDiscount) {
        return bxGyDiscount.getBuyProducts().stream().allMatch(buyProduct ->
                items.stream().anyMatch(item ->
                        item.getProductId() == buyProduct.getProductId() && item.getQuantity() >= buyProduct.getQuantity()
                )
        );
    }

    private double calculateBxGyDiscount(List<CartWrapperDTO.CartRequest.Item> items, BxGyDiscount bxGyDiscount) {
        double discountValue = 0;
        for (BxGyGetProduct getProduct : bxGyDiscount.getGetProducts()) {
            for (CartWrapperDTO.CartRequest.Item cartItem : items) {
                if (cartItem.getProductId() == getProduct.getProductId()) {
                    discountValue += getProduct.getQuantity() * cartItem.getPrice();
                }
            }
        }
        return discountValue;
    }

    @Override
    public UpdatedCartResponseDTO applyCouponById(Long couponId, CartWrapperDTO cartWrapperDTO) {
        if (cartWrapperDTO == null || cartWrapperDTO.getCart() == null || cartWrapperDTO.getCart().getItems().isEmpty()) {
            throw new InvalidInputException("Cart is empty or invalid.");
        }

        UpdatedCartResponseDTO response = new UpdatedCartResponseDTO();
        UpdatedCartResponseDTO.UpdatedCart updatedCart = new UpdatedCartResponseDTO.UpdatedCart();
        List<UpdatedCartResponseDTO.UpdatedCart.CartItem> updatedItems = new ArrayList<>();

        // Find the discount
        BxGyDiscount bxGyDiscount = bxGyDiscountRepository.findById(couponId)
                .orElseThrow(() -> new CouponNotFoundException("Coupon not found with ID: " + couponId));

        double totalPrice = 0;
        double totalDiscount = 0;

        // Process each item in the cart
        for (CartWrapperDTO.CartRequest.Item item : cartWrapperDTO.getCart().getItems()) {
            double itemPrice = item.getPrice() * item.getQuantity();
            totalPrice += itemPrice;

            double itemTotalDiscount = getItemTotalDiscount(item, bxGyDiscount);

            // Create updated item response with expected naming
            UpdatedCartResponseDTO.UpdatedCart.CartItem updatedItem = new UpdatedCartResponseDTO.UpdatedCart.CartItem(
                    item.getProductId(),
                    item.getQuantity(),
                    item.getPrice(),
                    itemTotalDiscount
            );

            updatedItems.add(updatedItem);
            totalDiscount += itemTotalDiscount;
        }

        updatedCart.setItems(updatedItems);
        updatedCart.setTotalPrice(totalPrice);
        updatedCart.setTotalDiscount(totalDiscount);
        updatedCart.setFinalPrice(totalPrice - totalDiscount);

        response.setUpdatedCart(updatedCart);
        return response;
    }

    private static double getItemTotalDiscount(CartWrapperDTO.CartRequest.Item item, BxGyDiscount bxGyDiscount) {
        double itemTotalDiscount = 0;

        // Check for applicable BxGy discounts
        for (BxGyBuyProduct buyProduct : bxGyDiscount.getBuyProducts()) {
            if (buyProduct.getProductId().equals((long) item.getProductId())) {
                // Calculate applicable discounts
                int applicableQuantity = Math.min(item.getQuantity(), buyProduct.getQuantity());
                for (BxGyGetProduct getProduct : bxGyDiscount.getGetProducts()) {
                    if (!getProduct.getProductId().equals((long) item.getProductId())) {
                        // Apply discount only for free items
                        itemTotalDiscount += (applicableQuantity / buyProduct.getQuantity()) * getProduct.getQuantity() * item.getPrice();
                    }
                }
            }
        }
        return itemTotalDiscount;
    }

}
