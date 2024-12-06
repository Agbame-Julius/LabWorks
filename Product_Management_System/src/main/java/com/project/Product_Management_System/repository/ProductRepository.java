package com.project.Product_Management_System.repository;

import com.project.Product_Management_System.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);

    void deleteByName(String name);
}
