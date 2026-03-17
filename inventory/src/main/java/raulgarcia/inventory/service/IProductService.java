package raulgarcia.inventory.service;

import raulgarcia.inventory.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProducts();

    List<Product> searchProducts(String text);

    Optional<Product> getProductById(Long id);

    Optional<Product> getProductBySku(String sku);

    Product saveProduct(Product product);

    void deleteProductById(Long id);

}
