package com.seyrek.vendingmachine.services;

import com.seyrek.vendingmachine.entities.Product;
import com.seyrek.vendingmachine.exceptions.ProductNotFoundException;
import com.seyrek.vendingmachine.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Executable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository mockProductRepository;
    @InjectMocks
    ProductService underTest;

    @Test
    void getAll_shouldReturnProductList() {
        Product product = new Product();
        product.setName("Water");
        List<Product> productList = Collections.singletonList(product);
        Product response = new Product();
        response.setName(product.getName());
        when(mockProductRepository.findAll()).thenReturn(productList);
        List<Product> actual = underTest.getAllProducts();
        assertEquals(product.getName(), actual.get(0).getName());
    }

    @Test
    void getById_shouldReturnProduct() {
        Product product = new Product();
        product.setName("Water");
        Product response = new Product();
        response.setName(product.getName());
        when(mockProductRepository.findById(anyInt())).thenReturn(Optional.of(product));
        Product actual = underTest.getProductById(1);
        assertEquals(response, actual);
    }

    @Test
    void getById_shouldThrowNotFound_whenProductNotFound(){
        when(mockProductRepository.findById(anyInt())).thenReturn(Optional.empty());
       assertThrows(ProductNotFoundException.class, () -> underTest.getProductById(1));
    }
}