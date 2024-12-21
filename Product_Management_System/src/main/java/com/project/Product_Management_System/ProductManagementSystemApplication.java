package com.project.Product_Management_System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductManagementSystemApplication {

	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "development");
		SpringApplication.run(ProductManagementSystemApplication.class, args);
	}

}
