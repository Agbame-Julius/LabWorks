//package com.project.Product_Management_System.service;
//
//
//import com.project.Product_Management_System.entity.Category;
//import com.project.Product_Management_System.repository.CategoryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CategoryService {
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    public Category addCategory(String name, Integer parentId, boolean isLeftChild) throws Throwable {
//        Category category = new Category();
//        category.setName(name);
//
//        if (parentId != null) {
//            Category parent = (Category) categoryRepository.findById(parentId)
//                    .orElseThrow(() -> new IllegalArgumentException("Parent category not found"));
//            category.setParent(parent);
//
//            if (isLeftChild) {
//                parent.setLeftChild(category);
//            } else {
//                parent.setRightChild(category);
//            }
//            categoryRepository.save(parent); // Save parent update
//        }
//
//        return (Category) categoryRepository.save(category);
//    }
//}
