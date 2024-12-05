package com.project.Product_Management_System.controller;

import com.project.Product_Management_System.entity.Product;
import com.project.Product_Management_System.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    //adding a single product
    @PostMapping("/product")
    public Product saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    //adding multiple products
    @PostMapping("/products")
    public List<Product> addProducts(@RequestBody List<Product> products){
        return productService.saveProducts(products);
    }


    //getting all the products
    @GetMapping("/products")
    public List<Product> listProducts(){
        return productService.getProducts();
    }

    //getting sorted products
    @GetMapping("/{field}")
    public List<Product> getSortedProducts(@PathVariable String field){
        return productService.getProductsSorting(field);
    }

    //getting product by id
    @GetMapping("productById/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.getProductById(id);

    }//getting product by name
    @GetMapping("productByName/{name}")
    public Product getProductByName(@PathVariable String name){
        return productService.getProductByName(name);
    }

    //deleting a product by id
    @DeleteMapping("/deleteById/{id}")
    public void deleteProductById(@PathVariable("id") int id){
        productService.deleteProductById(id);
    }

    //deleting product by name
    @DeleteMapping("/deleteByName/{name}")
    public void deleteByProductName(String name){
        productService.deleteProductByName(name);
    }

    //Updating the product
    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product){
        return productService.udpdateProduct(product);
    }

    //paginated Products
    @GetMapping("/pagination/{offset}/{pageSize}")
    public Page<Product> getPagainatedProducts(@PathVariable int offset, @PathVariable int pageSize){
        Page<Product> products = productService.paginatedProduct(offset, pageSize);
        return products;
    }
    @GetMapping("/paginate/{offset}/{pageSize}/{field}")
    public Page<Product> getPagainatedAndSortedProducts(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        Page<Product> products = productService.paginatedAndSortedProduct(offset, pageSize, field);
        return products;
    }
}
