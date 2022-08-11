package Controller;

import DTO.ProductDTO;
import Model.Product;
import Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: comments

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("all/undeleted")
    public List<Product> getUndeletedProducts() {
        return productService.getUndeletedProducts();
    }

    @GetMapping("dto/all/undeleted")
    public List<ProductDTO> getUndeletedProductsDTO() {
        return productService.getUndeletedProductsDTO();
    }

    @GetMapping("all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("dto/all")
    public List<ProductDTO> getAllProductsDTO() {
        return productService.getAllProductsDTO();
    }

    @PostMapping("add")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PostMapping("dto/add")
    public ProductDTO addProductDTO(@RequestBody Product product) {
        return productService.addProductDTO(product);
    }

    @PutMapping("delete/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

    @PutMapping("update/{id}/{typeOfUpdate}/{value}")
    public Product updateProductStock(@PathVariable Integer id, @PathVariable String typeOfUpdate, @PathVariable Integer value) {
        return productService.updateProductStock(id, typeOfUpdate, value);
    }

    @PutMapping("update/{id}/increment")
    public Product incrementProduct(@PathVariable Integer id) {
        return productService.incrementProduct(id);
    }

    @PutMapping("update/{id}/decrement")
    public Product decrementProduct(@PathVariable Integer id) {
        return productService.decrementProduct(id);
    }
}
