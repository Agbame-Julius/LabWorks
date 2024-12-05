//package com.project.Product_Management_System.controller;
//
//
//import com.project.Product_Management_System.entity.Category;
//import com.project.Product_Management_System.service.CategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/category")
//public class CategoryController {
//    @Autowired
//    private CategoryService categoryService;
//
//    @PostMapping("/add")
//    public Category addCategory(@PathVariable String name, @PathVariable Integer parentId, @PathVariable Boolean isLeftChild) throws Throwable {
//
//        return categoryService.addCategory(name,parentId,isLeftChild);
//    }
//}
