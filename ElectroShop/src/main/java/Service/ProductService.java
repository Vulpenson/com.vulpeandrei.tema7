package Service;

import DTO.ProductDTO;
import Mapper.ProductMapper;
import Model.Product;
import Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    // Method to get all undeleted products
    public List<Product> getUndeletedProducts() {
        List<Product> tmp = productRepository.findAll();
        tmp.removeIf(product -> !product.getDeleted());
        return tmp;
    }

    // Method to get all undeleted productsDTO
    public List<ProductDTO> getUndeletedProductsDTO() {
        return productRepository.findAll().stream()
                .filter(x -> !x.getDeleted())
                .map(productMapper::toProductDTO)
                .collect(Collectors.toList());
    }

    // Method to get all products, even those deleted
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Method to get all productsDTO, even those deleted
    public List<ProductDTO> getAllProductsDTO() {
        return productRepository.findAll().stream().map(productMapper::toProductDTO).collect(Collectors.toList());
    }

    // Method to add a product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Method to delete a product by id
    public void deleteProduct(Integer id) {
        productRepository.findById(id).get().setDeleted(true);
    }

    // Method to update a product stock (minus, plus)
    public Product updateProductStock(Integer id, String typeOfUpdate, Integer value) {
        Product tmp = productRepository.findById(id).get();
        int initialValue = tmp.getInitialStock().get();
        if (typeOfUpdate.equals("plus")) {
            tmp.getInitialStock().set(initialValue + value);
        }
        if (typeOfUpdate.equals("minus")){
            tmp.getInitialStock().set(initialValue - value);
        }
        return productRepository.save(tmp);
    }

    // Method to increment a product stock
    public Product incrementProductStock(Integer id) {
        Product tmp = productRepository.findById(id).get();
        tmp.getInitialStock().incrementAndGet();
        return productRepository.save(tmp);
    }

    // Method to decrement a product stock
    public Product decrementProductStock(Integer id) {
        Product tmp = productRepository.findById(id).get();
        tmp.getInitialStock().decrementAndGet();
        return productRepository.save(tmp);
    }
}
