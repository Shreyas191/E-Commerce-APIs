package com.ecommerce.coupons.repository;

import com.ecommerce.coupons.entity.Coupon;
import com.ecommerce.coupons.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
