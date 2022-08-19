package Controller;

import DTO.ProductDTO;
import Exception.InsufficientStockException;
import Model.Product;
import Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductService productService;

    // Method to get all undeleted products
    @GetMapping("all/undeleted")
    public List<Product> getUndeletedProducts() {
        return productService.getUndeletedProducts();
    }

    // Method to get all undeleted productsDTO
    @GetMapping("dto/all/undeleted")
    public List<ProductDTO> getUndeletedProductsDTO() {
        return productService.getUndeletedProductsDTO();
    }

    // Method to get all products, even those deleted
    @GetMapping("all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Method to get all productsDTO, even those deleted
    @GetMapping("dto/all")
    public List<ProductDTO> getAllProductsDTO() {
        return productService.getAllProductsDTO();
    }

    // Method to add a product
    @PostMapping("add")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // Method to delete a product by id
    @PutMapping("delete/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

    // Method to update a product stock (minus, plus)
    @PutMapping("update/{id}/{typeOfUpdate}/{value}")
    public Product updateProductStock(@PathVariable Integer id, @PathVariable String typeOfUpdate, @PathVariable Integer value) {
        return productService.updateProductStock(id, typeOfUpdate, value);
    }

    // Method to increment a product stock
    @PutMapping("update/{id}/increment")
    public Product incrementProduct(@PathVariable Integer id) {
        return productService.incrementProductStock(id);
    }

    // Method to decrement a product stock
    @PutMapping("update/{id}/decrement")
    public Product decrementProduct(@PathVariable Integer id) throws InsufficientStockException {
        return productService.decrementProductStock(id);
    }
}
