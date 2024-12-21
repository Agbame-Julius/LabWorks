package com.project.Product_Management_System.repository;


import com.project.Product_Management_System.entity.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<ProductReview, Integer> {
    List<ProductReview> findByProductId(Integer productId);
}
