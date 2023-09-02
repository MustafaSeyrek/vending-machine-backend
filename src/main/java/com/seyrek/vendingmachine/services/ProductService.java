package com.seyrek.vendingmachine.services;

import com.seyrek.vendingmachine.entities.Product;
import com.seyrek.vendingmachine.exceptions.ProductNotFoundException;
import com.seyrek.vendingmachine.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void updateProductById(int id, Product product) {
        Product oldProduct = getProductById(id);
        oldProduct.setStock(product.getStock());
        oldProduct.setPrice(product.getPrice());
        productRepository.save(oldProduct);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found id: " + id));
    }
}
