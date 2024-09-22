package com.ecommerce.coupons.controller;
import com.ecommerce.coupons.dto.DiscountDTO;
import com.ecommerce.coupons.exception.NullRequestBodyException;
import com.ecommerce.coupons.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discounts")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/coupons")
    public ResponseEntity<String> applyDiscount(@RequestBody DiscountDTO discountDTORequest) {

        if(discountDTORequest == null){
            throw new NullRequestBodyException("Null request received");
        }

        String result = discountService.handleDiscountRequest(discountDTORequest);
        return ResponseEntity.ok(result);
    }
}

