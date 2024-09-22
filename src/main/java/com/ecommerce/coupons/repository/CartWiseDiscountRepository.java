package com.ecommerce.coupons.repository;

import com.ecommerce.coupons.entity.CartWiseDiscount;
import com.ecommerce.coupons.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartWiseDiscountRepository extends JpaRepository<CartWiseDiscount, Long> {
    List<CartWiseDiscount> findByThresholdLessThanEqual(double threshold);

    @Query("SELECT c FROM CartWiseDiscount c WHERE c.threshold <= :threshold")
    List<CartWiseDiscount> findApplicableDiscounts(@Param("threshold") double threshold);

    List<CartWiseDiscount> findByDiscount(Discount discount);
}
