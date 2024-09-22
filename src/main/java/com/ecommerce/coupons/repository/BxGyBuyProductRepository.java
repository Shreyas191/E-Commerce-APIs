package com.ecommerce.coupons.repository;

import com.ecommerce.coupons.entity.BxGyBuyProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BxGyBuyProductRepository extends JpaRepository<BxGyBuyProduct, Long> {
}
