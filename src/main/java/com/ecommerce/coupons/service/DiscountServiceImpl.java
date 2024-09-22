package com.ecommerce.coupons.service;

import com.ecommerce.coupons.entity.*;
import com.ecommerce.coupons.exception.InvalidInputException;
import com.ecommerce.coupons.dto.BxGyDiscountDTO;
import com.ecommerce.coupons.dto.CartWiseDiscountDTO;
import com.ecommerce.coupons.dto.DiscountDTO;
import com.ecommerce.coupons.dto.ProductWiseDiscountDTO;
import com.ecommerce.coupons.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final CartWiseDiscountRepository cartWiseDiscountRepository;
    private final DiscountRepository discountRepository;
    private final ProductWiseDiscountRepository productWiseDiscountRepository;
    private final BxGyBuyProductRepository bxGyBuyProductRepository;
    private final BxGyGetProductRepository bxGyGetProductRepository;
    private final BxGyDiscountRepository bxGyDiscountRepository;

    @Autowired
    public DiscountServiceImpl(CartWiseDiscountRepository cartWiseDiscountRepository, DiscountRepository discountRepository, ProductWiseDiscountRepository productWiseDiscountRepository, BxGyBuyProductRepository bxGyBuyProductRepository, BxGyGetProductRepository bxGyGetProductRepository, BxGyDiscountRepository bxGyDiscountRepository) {
        this.cartWiseDiscountRepository = cartWiseDiscountRepository;
        this.discountRepository = discountRepository;
        this.productWiseDiscountRepository = productWiseDiscountRepository;
        this.bxGyBuyProductRepository = bxGyBuyProductRepository;
        this.bxGyGetProductRepository = bxGyGetProductRepository;
        this.bxGyDiscountRepository = bxGyDiscountRepository;
    }

    public String handleDiscountRequest(DiscountDTO discountDTORequest) {
        if (discountDTORequest == null || discountDTORequest.getType() == null) {
            throw new InvalidInputException("Discount request cannot be null or empty.");
        }

        switch (discountDTORequest.getType()) {
            case "cart-wise":
                CartWiseDiscountDTO cartWiseDiscountDTO = (CartWiseDiscountDTO) discountDTORequest;
                return handleCartWiseDiscount(cartWiseDiscountDTO);

            case "product-wise":
                ProductWiseDiscountDTO productWiseDiscount = (ProductWiseDiscountDTO) discountDTORequest;
                return handleProductWiseDiscount(productWiseDiscount);

            case "bxgy":
                BxGyDiscountDTO bxgyDiscount = (BxGyDiscountDTO) discountDTORequest;
                return handleBxGyDiscount(bxgyDiscount);

            default:
                throw new IllegalArgumentException("Unknown discount type: " + discountDTORequest.getType());
        }
    }

    public String handleCartWiseDiscount(CartWiseDiscountDTO discountRequest) {
        if (discountRequest == null || discountRequest.getDetails() == null || discountRequest.getDetails().getThreshold() <= 0) {
            throw new InvalidInputException("Invalid cart-wise discount request.");
        }

        // Business logic for cart-wise discount
        CartWiseDiscount discountEntity = new CartWiseDiscount();
        discountEntity.setThreshold(discountRequest.getDetails().getThreshold());
        discountEntity.setDiscountValue(discountRequest.getDetails().getDiscount());

        Discount discount = new Discount();
        discount.setType(discountRequest.getType());
        discountEntity.setDiscount(discount);

        discountRepository.save(discount);
        cartWiseDiscountRepository.save(discountEntity);

        return "Cart-wise discount applied with threshold " + discountRequest.getDetails().getThreshold();
    }

    public String handleProductWiseDiscount(ProductWiseDiscountDTO discountRequest) {
        if (discountRequest == null || discountRequest.getDetails() == null || discountRequest.getDetails().getProductId() <= 0) {
            throw new InvalidInputException("Invalid product-wise discount request.");
        }

        // Business logic for product-wise discount
        ProductWiseDiscount productWiseDiscount = new ProductWiseDiscount();
        productWiseDiscount.setDiscountValue(discountRequest.getDetails().getDiscount());
        productWiseDiscount.setProductId(discountRequest.getDetails().getProductId());

        Discount discount = new Discount();
        discount.setType(discountRequest.getType());
        productWiseDiscount.setDiscount(discount);

        discountRepository.save(discount);
        productWiseDiscountRepository.save(productWiseDiscount);

        return "Product-wise discount applied for product ID " + discountRequest.getDetails().getProductId();
    }

    public String handleBxGyDiscount(BxGyDiscountDTO discountRequest) {
        if (discountRequest == null || discountRequest.getDetails() == null || discountRequest.getDetails().getRepitionLimit() <= 0) {
            throw new InvalidInputException("Invalid BxGy discount request.");
        }

        // Business logic for BxGy discount
        BxGyDiscount bxGyDiscount = new BxGyDiscount();
        bxGyDiscount.setRepitionLimit(discountRequest.getDetails().getRepitionLimit());

        Discount discount = new Discount();
        discount.setType(discountRequest.getType());
        bxGyDiscount.setDiscount(discount);

        discountRepository.save(discount);
        bxGyDiscountRepository.save(bxGyDiscount);

        // Save Buy Products
        for (BxGyDiscountDTO.BxGyDetails.Product buyProduct : discountRequest.getDetails().getBuyProducts()) {
            if (buyProduct.getProductId() <= 0 || buyProduct.getQuantity() <= 0) {
                throw new InvalidInputException("Invalid buy product details.");
            }
            BxGyBuyProduct bxGyBuyProduct = new BxGyBuyProduct();
            bxGyBuyProduct.setProductId(buyProduct.getProductId());
            bxGyBuyProduct.setQuantity(buyProduct.getQuantity());
            bxGyBuyProduct.setBxGyDiscount(bxGyDiscount);

            bxGyBuyProductRepository.save(bxGyBuyProduct);
        }

        // Save Get Products
        for (BxGyDiscountDTO.BxGyDetails.Product getProduct : discountRequest.getDetails().getGetProducts()) {
            if (getProduct.getProductId() <= 0 || getProduct.getQuantity() <= 0) {
                throw new InvalidInputException("Invalid get product details.");
            }
            BxGyGetProduct bxGyGetProduct = new BxGyGetProduct();
            bxGyGetProduct.setProductId(getProduct.getProductId());
            bxGyGetProduct.setQuantity(getProduct.getQuantity());
            bxGyGetProduct.setBxGyDiscount(bxGyDiscount);

            bxGyGetProductRepository.save(bxGyGetProduct);
        }

        return "Buy X Get Y discount applied with repetition limit " + discountRequest.getDetails().getRepitionLimit();
    }
}
