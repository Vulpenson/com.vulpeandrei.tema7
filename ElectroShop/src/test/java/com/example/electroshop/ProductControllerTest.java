package com.example.electroshop;

import Controller.ProductController;
import Model.Product;
import Model.Type;
import Service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @MockBean
    ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void it_should_get_all_products() throws Exception {
        Product testProduct = new Product(5,
                "Boxa portabila",
                new AtomicInteger(100),
                Type.ELC,
                false);
        mockMvc.perform(get("products/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void it_should_get_all_products_undeleted() throws Exception {
        Product testProduct = new Product(5,
                "Boxa portabila",
                new AtomicInteger(100),
                Type.ELC,
                false);
        mockMvc.perform(get("products/all/undeleted"))
                .andExpect(status().isOk());
    }

    @Test
    public void it_should_delete_product() throws Exception {
        Product testProduct = new Product(5,
                "Boxa portabila",
                new AtomicInteger(100),
                Type.ELC,
                false);
        mockMvc.perform(put("products/delete/5"))
                .andExpect(status().isOk());
    }
}
