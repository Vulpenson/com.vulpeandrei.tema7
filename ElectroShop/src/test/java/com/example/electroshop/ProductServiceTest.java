package com.example.electroshop;

import Model.Product;
import Model.Type;
import Repository.ProductRepository;
import Service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    public void it_should_increment_product_stock_count() {
        Product testProduct = new Product(5,
                                            "Boxa portabila",
                                            new AtomicInteger(100),
                                            Type.ELC,
                                            false);
        when(productRepository.save(any(Product.class))).thenReturn(new Product());
        Product createdProduct = productService.addProduct(testProduct);
        productService.incrementProductStock(testProduct.getId());
        assertThat(createdProduct.getInitialStock().get()).isSameAs(testProduct.getInitialStock().get());
    }


}
