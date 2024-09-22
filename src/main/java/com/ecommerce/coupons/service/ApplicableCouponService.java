package com.ecommerce.coupons.service;

import com.ecommerce.coupons.dto.*;

import java.util.List;

public interface ApplicableCouponService {

    public List<CouponResponseDTO.ApplicableCoupon> findApplicableCoupons(CartWrapperDTO cartWrapperDTO);

    public UpdatedCartResponseDTO applyCouponById(Long couponId, CartWrapperDTO cartWrapperDTO);

    //List<CouponResponse> getApplicableCoupons(CartRequest cartRequest);
}
