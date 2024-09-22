package com.ecommerce.coupons.controller;

import com.ecommerce.coupons.dto.CartWrapperDTO;
import com.ecommerce.coupons.dto.CouponResponseDTO;
import com.ecommerce.coupons.dto.UpdatedCartResponseDTO;
import com.ecommerce.coupons.exception.NullRequestBodyException;
import com.ecommerce.coupons.service.ApplicableCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
public class ApplicableCouponController {

    private final ApplicableCouponService applicableCouponService;

    @Autowired
    public ApplicableCouponController(ApplicableCouponService applicableCouponService) {
        this.applicableCouponService = applicableCouponService;
    }

    @PostMapping("/applicable-coupons")
    public ResponseEntity<List<CouponResponseDTO.ApplicableCoupon>> getApplicableCoupons(@RequestBody CartWrapperDTO cartWrapperDTO) {

        if(cartWrapperDTO == null){
            throw new NullRequestBodyException("Null request received");
        }

        List<CouponResponseDTO.ApplicableCoupon> applicableCoupons = applicableCouponService.findApplicableCoupons(cartWrapperDTO);
        return ResponseEntity.ok(applicableCoupons);
    }

    @PostMapping("/apply-coupon/{id}")
    public ResponseEntity<UpdatedCartResponseDTO> applyCoupon(@PathVariable Long id, @RequestBody CartWrapperDTO cartWrapperDTO) {
        if(cartWrapperDTO == null){
            throw new NullRequestBodyException("Null request received");
        }

        UpdatedCartResponseDTO response = applicableCouponService.applyCouponById(id, cartWrapperDTO);
        return ResponseEntity.ok(response);
    }
}
