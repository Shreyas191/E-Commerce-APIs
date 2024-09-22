package com.ecommerce.coupons.repository;

import com.ecommerce.coupons.entity.ProductWiseDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductWiseDiscountRepository extends JpaRepository<ProductWiseDiscount, Long> {
    ProductWiseDiscount findByProductId(int productId);
}
