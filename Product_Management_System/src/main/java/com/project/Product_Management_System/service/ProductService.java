package com.project.Product_Management_System.service;

import com.project.Product_Management_System.entity.Product;
import com.project.Product_Management_System.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

import java.util.List;

@Service
public class ProductService{

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product){
        return repository.save(product);
    }

    public List<Product> saveProducts(List<Product> products){
        return repository.saveAll(products);
    }

    public List<Product> getProducts(){
        return repository.findAll();
    }

    //sorting the products dynamically
    public List<Product> getProductsSorting(String field){
        return repository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    public Product getProductById(int id){
        return repository.findById(id).orElse(null);
    }

    public Product getProductByName(String name){
        return repository.findByName(name);
    }

    public String deleteProductById(int id){
        repository.deleteById(id);
        return "Product" + id + " deleted successfully";
    }

    public String deleteProductByName(String name){
        repository.deleteByName(name);
        return name + "Successfully deleted!";
    }

    public Product udpdateProduct(Product product){
        Product existingProduct = repository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());

        return repository.save(existingProduct);

    }


    //paginated products
    public Page<Product> paginatedProduct(int offset, int pageSize){
        Page<Product> products = repository.findAll(PageRequest.of(offset, pageSize));
        return products;
    }

    //paginated with sorted products
    public Page<Product> paginatedAndSortedProduct(int offset, int pageSize, String field){
        Page<Product> products = repository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.Direction.ASC, field));
        return products;
    }
}
