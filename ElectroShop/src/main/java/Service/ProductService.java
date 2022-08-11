package Service;

import DTO.ProductDTO;
import Mapper.ProductMapper;
import Model.Product;
import Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<Product> getNonDeletedProducts() {
        List<Product> tmp = productRepository.findAll();
        for (Product product: tmp) {
            if(!product.getDeleted()) {
                tmp.remove(product);
            }
        }
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

    public void updateProductStock(Integer id, String typeOfUpdate) {
        Product tmp = productRepository.findById(id).get();
    }
}
