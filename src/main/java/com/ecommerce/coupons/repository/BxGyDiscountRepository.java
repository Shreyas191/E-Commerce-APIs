package com.ecommerce.coupons.repository;

import com.ecommerce.coupons.entity.BxGyDiscount;
import com.ecommerce.coupons.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BxGyDiscountRepository extends JpaRepository<BxGyDiscount, Long> {


    @Query("SELECT b FROM BxGyDiscount b WHERE b.repitionLimit <= :limit")
    List<BxGyDiscount> findApplicableBxGyDiscount(@Param("limit") int limit);

    List<BxGyDiscount> findByDiscount(Discount discount);
}
