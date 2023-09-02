package com.seyrek.vendingmachine.controllers;

import com.seyrek.vendingmachine.entities.Product;
import com.seyrek.vendingmachine.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    ProductService mockProductService;
    @InjectMocks
    ProductController underTest;

    @Test
    void getAll_shouldReturnProductList() {
        Product product = new Product();
        List<Product> expected = Arrays.asList(product);

        when(mockProductService.getAllProducts()).thenReturn(expected);
        ResponseEntity<List<Product>> response = underTest.getAllProducts();
        List<Product> actual = response.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expected.size(), actual.size())
        );
    }

    @Test
    void getById_shouldReturnProduct() {
        Product expected = new Product();
        expected.setName("Water");
        when(mockProductService.getProductById(anyInt())).thenReturn(expected);

        ResponseEntity<Product> response = underTest.getProductById(1);
        Product actual = response.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expected.getName(), actual.getName())
        );
    }

    @Test
    void getById_shouldReturnStatusNotFound_whenProductNotExist() {
        when(mockProductService.getProductById(anyInt())).thenReturn(null);
        ResponseEntity<Product> response = underTest.getProductById(1);
        Product actual = response.getBody();
        assertNull(actual);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}