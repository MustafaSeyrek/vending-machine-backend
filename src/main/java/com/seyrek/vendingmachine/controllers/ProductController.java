package com.seyrek.vendingmachine.controllers;

import com.seyrek.vendingmachine.entities.Product;
import com.seyrek.vendingmachine.exceptions.ProductNotFoundException;
import com.seyrek.vendingmachine.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return new ResponseEntity<>(getProduct(id), OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProductById(@PathVariable int id, @RequestBody Product product){
        productService.updateProductById(id, product);
        return new ResponseEntity<>(OK);
    }

    private Product getProduct(int id) {
        return productService.getProductById(id);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }
}
