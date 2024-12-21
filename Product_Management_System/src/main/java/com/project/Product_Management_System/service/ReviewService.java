package com.project.Product_Management_System.service;

import com.project.Product_Management_System.entity.ProductReview;
import com.project.Product_Management_System.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<ProductReview> getReviewsForProduct(Integer productId) {
        return reviewRepository.findByProductId(productId);
    }

    public ProductReview addReview(ProductReview review) {
        return reviewRepository.save(review);
    }

    public List<ProductReview> getAllReviews() {
        return reviewRepository.findAll();
    }
}
