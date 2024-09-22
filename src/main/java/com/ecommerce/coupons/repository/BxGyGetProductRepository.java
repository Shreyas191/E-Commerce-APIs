package com.ecommerce.coupons.repository;

import com.ecommerce.coupons.entity.BxGyGetProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BxGyGetProductRepository extends JpaRepository<BxGyGetProduct, Long> {
}
