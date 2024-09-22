package com.ecommerce.coupons.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CouponResponseDTO {
    private List<ApplicableCoupon> applicableCoupons;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ApplicableCoupon {
        private Long couponId;
        private String type;
        private double discount;
    }
}
