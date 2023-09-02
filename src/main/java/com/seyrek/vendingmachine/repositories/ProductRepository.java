package com.seyrek.vendingmachine.repositories;

import com.seyrek.vendingmachine.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
