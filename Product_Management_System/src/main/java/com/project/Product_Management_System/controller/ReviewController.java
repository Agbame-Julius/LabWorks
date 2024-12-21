package com.project.Product_Management_System.controller;

import com.project.Product_Management_System.entity.ProductReview;
import com.project.Product_Management_System.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public List<ProductReview> listReviews(){
        return reviewService.getAllReviews();
    }

    @PostMapping("/add-review")
    public ProductReview addReview(@RequestBody ProductReview productReview){
        return reviewService.addReview(productReview);
    }

    @GetMapping("/{productId}")
    public List<ProductReview> getReviewById(@PathVariable Integer productId){
        return reviewService.getReviewsForProduct(productId);
    }
}
