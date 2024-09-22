package com.ecommerce.coupons.repository;

import com.ecommerce.coupons.entity.BxgyCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BxgyCouponRepository extends JpaRepository<BxgyCoupon, Long> {
}
