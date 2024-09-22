package com.ecommerce.coupons.repository;

import com.ecommerce.coupons.entity.CartWiseCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartWiseCouponRepository extends JpaRepository<CartWiseCoupon, Long> {
}
