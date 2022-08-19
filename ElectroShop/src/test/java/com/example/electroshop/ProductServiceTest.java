package com.example.electroshop;

import Model.Product;
import Model.Type;
import Repository.ProductRepository;
import Service.ProductService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.atomic.AtomicInteger;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    public void it_should_increment_product_stock_count() {
        Product testProduct = new Product(null,
                                            "Boxa portabila",
                                            new AtomicInteger(100),
                                            Type.ELC,
                                            false);

    }
}
