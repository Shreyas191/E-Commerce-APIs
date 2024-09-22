package com.ecommerce.coupons.service;

import com.ecommerce.coupons.entity.*;
import com.ecommerce.coupons.exception.InvalidInputException;
import com.ecommerce.coupons.dto.BxGyDiscountDTO;
import com.ecommerce.coupons.dto.CartWiseDiscountDTO;
import com.ecommerce.coupons.dto.DiscountDTO;
import com.ecommerce.coupons.dto.ProductWiseDiscountDTO;
import com.ecommerce.coupons.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiscountServiceImplTest {

    @Mock
    private CartWiseDiscountRepository cartWiseDiscountRepository;
    @Mock
    private DiscountRepository discountRepository;
    @Mock
    private ProductWiseDiscountRepository productWiseDiscountRepository;
    @Mock
    private BxGyBuyProductRepository bxGyBuyProductRepository;
    @Mock
    private BxGyGetProductRepository bxGyGetProductRepository;
    @Mock
    private BxGyDiscountRepository bxGyDiscountRepository;

    @InjectMocks
    private DiscountServiceImpl discountService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testHandleCartWiseDiscount_ValidRequest() {
        // Arrange
        CartWiseDiscountDTO discountDTO = new CartWiseDiscountDTO();
        discountDTO.setDetails(new CartWiseDiscountDTO.Details(100, 10));

        // Act
        String result = discountService.handleCartWiseDiscount(discountDTO);

        // Assert
        assertEquals("Cart-wise discount applied with threshold 100", result);
        verify(discountRepository, times(1)).save(any(Discount.class));
        verify(cartWiseDiscountRepository, times(1)).save(any(CartWiseDiscount.class));
    }

    @Test
    void testHandleCartWiseDiscount_InvalidRequest() {
        // Arrange
        CartWiseDiscountDTO discountDTO = new CartWiseDiscountDTO();
        discountDTO.setDetails(new CartWiseDiscountDTO.Details(-1, 10)); // Invalid threshold

        // Act & Assert
        assertThrows(InvalidInputException.class, () -> discountService.handleCartWiseDiscount(discountDTO));
    }

    @Test
    void testHandleProductWiseDiscount_ValidRequest() {
        // Arrange
        ProductWiseDiscountDTO discountDTO = new ProductWiseDiscountDTO();
        discountDTO.setDetails(new ProductWiseDiscountDTO.ProductWiseDetails(10, 1));

        // Act
        String result = discountService.handleProductWiseDiscount(discountDTO);

        // Assert
        assertEquals("Product-wise discount applied for product ID 1", result);
        verify(discountRepository, times(1)).save(any(Discount.class));
        verify(productWiseDiscountRepository, times(1)).save(any(ProductWiseDiscount.class));
    }

    @Test
    void testHandleBxGyDiscount_ValidRequest() {

        BxGyDiscountDTO discountDTO = new BxGyDiscountDTO();
        //discountDTO.setDetails(new BxGyDiscountDTO.BxGyDetails(5, List.of(new BxGyDiscountDTO.BxGyDetails.Product(1L, 2)));

        String result = discountService.handleBxGyDiscount(discountDTO);


        assertEquals("Buy X Get Y discount applied with repetition limit 5", result);
        verify(discountRepository, times(1)).save(any(Discount.class));
        verify(bxGyDiscountRepository, times(1)).save(any(BxGyDiscount.class));
        verify(bxGyBuyProductRepository, times(1)).save(any(BxGyBuyProduct.class));
        verify(bxGyGetProductRepository, times(1)).save(any(BxGyGetProduct.class));
    }

    @Test
    void testHandleDiscountRequest_InvalidType() {
        // Arrange
        DiscountDTO discountDTO = new CartWiseDiscountDTO();
        discountDTO.setType("unknown");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> discountService.handleDiscountRequest(discountDTO));
    }

    @Test
    void testHandleDiscountRequest_NullRequest() {
        // Act & Assert
        assertThrows(InvalidInputException.class, () -> discountService.handleDiscountRequest(null));
    }
}
