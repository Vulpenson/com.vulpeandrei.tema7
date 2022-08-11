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

    public List<Product> getNonDeletedProducts() {
        List<Product> tmp = productRepository.findAll();
        tmp.removeIf(product -> !product.getDeleted());
        return tmp;
    }

    public List<ProductDTO> getNonDeletedProductsDTO() {
        return productRepository.findAll().stream()
                .filter(x -> !x.getDeleted())
                .map(productMapper::toProductDTO)
                .collect(Collectors.toList());
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<ProductDTO> getAllProductsDTO() {
        return productRepository.findAll().stream().map(productMapper::toProductDTO).collect(Collectors.toList());
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public ProductDTO addProductDTO(Product product) {
        return productMapper.toProductDTO(productRepository.save(product));
    }

    public void deleteProduct(Integer id) {
        productRepository.findById(id).get().setDeleted(true);
    }

    public Product updateProductStock(Integer id, String typeOfUpdate, Integer value) {
        Product tmp = productRepository.findById(id).get();
        int initialValue = tmp.getInitialStock().get();
        if (typeOfUpdate.equals("plus")) {
            tmp.getInitialStock().set(initialValue + value);
        } else {
            tmp.getInitialStock().set(initialValue - value);
        }
        return productRepository.save(tmp);
    }

    public Product incrementProduct(Integer id) {
        Product tmp = productRepository.findById(id).get();
        tmp.getInitialStock().incrementAndGet();
        return productRepository.save(tmp);
    }

    public Product decrementProduct(Integer id) {
        Product tmp = productRepository.findById(id).get();
        tmp.getInitialStock().decrementAndGet();
        return productRepository.save(tmp);
    }
}
