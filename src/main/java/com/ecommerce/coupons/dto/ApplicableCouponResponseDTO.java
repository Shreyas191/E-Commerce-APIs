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
public class ApplicableCouponResponseDTO {

    private List<Coupon> applicableCoupons;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Coupon {
        private Long couponId;
        private String type;
        private double discount;
    }
}
