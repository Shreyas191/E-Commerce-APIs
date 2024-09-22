package com.ecommerce.coupons.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bxgy_discounts")
public class BxGyDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "bxGyDiscount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BxGyBuyProduct> buyProducts;

    @OneToMany(mappedBy = "bxGyDiscount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BxGyGetProduct> getProducts;

    @ManyToOne
    @JoinColumn(name = "discount_id", nullable = false)
    private Discount discount;

    private int repitionLimit;

}
