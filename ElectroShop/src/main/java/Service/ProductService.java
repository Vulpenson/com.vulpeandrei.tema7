package Service;

import DTO.ProductDTO;
import Exception.InsufficientStockException;
import Mapper.ProductMapper;
import Model.Product;
import Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Optional<Product> tmpOptional = productRepository.findById(id);
        if(tmpOptional.isEmpty()) {
            return;
        }
        tmpOptional.get().setDeleted(true);
    }

    // Method to update a product stock (minus, plus)
    public Product updateProductStock(Integer id, String typeOfUpdate, Integer value) {
        Optional<Product> tmp = productRepository.findById(id);
        if(tmp.isEmpty()) {
            return null;
        }
        int initialValue = tmp.get().getInitialStock().get();
        if (typeOfUpdate.equals("plus")) {
            tmp.get().getInitialStock().set(initialValue + value);
        }
        if (typeOfUpdate.equals("minus")){
            tmp.get().getInitialStock().set(initialValue - value);
        }
        return productRepository.save(tmp.get());
    }

    // Method to increment a product stock
    public Product incrementProductStock(Integer id) {
        Optional<Product> tmpOptional = productRepository.findById(id);
        if(tmpOptional.isEmpty()) {
            return null;
        }
        tmpOptional.get().getInitialStock().incrementAndGet();
        return productRepository.save(tmpOptional.get());
    }

    // Method to decrement a product stock
    public Product decrementProductStock(Integer id) throws InsufficientStockException {
        Optional<Product> tmpOptional = productRepository.findById(id);
        if(tmpOptional.isEmpty()) {
            return null;
        }
        if(tmpOptional.get().getInitialStock().get() == 0) {
            throw new InsufficientStockException();
        } else {
            tmpOptional.get().getInitialStock().decrementAndGet();
            return productRepository.save(tmpOptional.get());
        }
    }
}
