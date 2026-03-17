package raulgarcia.inventory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raulgarcia.inventory.exceptions.DatabaseOperationException;
import raulgarcia.inventory.exceptions.DuplicateSkuException;
import raulgarcia.inventory.exceptions.ResourceNotFoundException;
import raulgarcia.inventory.model.Product;
import raulgarcia.inventory.repository.IProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public List<Product> searchProducts(String text) {
        return (text == null || text.isBlank())
                ? this.productRepository.findAll()
                : this.productRepository.searchProducts(text);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> getProductBySku(String sku) {
        return this.productRepository.findBySku(sku);
    }

    @Override
    @Transactional
    public Product saveProduct(Product product) {
        Optional<Product> existing = this.productRepository.findBySku(product.getSku());
        if (existing.isPresent() && !existing.get().getId().equals(product.getId())) {
            throw new DuplicateSkuException("SKU already taken by another product");
        }

        try {
            return this.productRepository.save(product);
        } catch (Exception e) {
            throw new DatabaseOperationException("Error persisting product", e);
        }
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product with ID " + id + "does not exist.");
        }
        
        try {
            this.productRepository.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseOperationException("Error deleting Product", e);
        }
    }

}
