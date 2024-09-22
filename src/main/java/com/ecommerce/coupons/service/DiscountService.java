package com.ecommerce.coupons.service;

import com.ecommerce.coupons.dto.*;

public interface DiscountService {

    String handleDiscountRequest(DiscountDTO discountDTORequest);
    String handleCartWiseDiscount(CartWiseDiscountDTO discountRequest);
    String handleProductWiseDiscount(ProductWiseDiscountDTO discountRequest);
    String handleBxGyDiscount(BxGyDiscountDTO discountRequest);
}
