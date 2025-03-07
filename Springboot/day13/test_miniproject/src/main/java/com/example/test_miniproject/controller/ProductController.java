package com.example.test_miniproject.controller;

import com.example.test_miniproject.model.Product;
import com.example.test_miniproject.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    //Lấy danh sách products
    @GetMapping("/products")
    public ResponseEntity<?> getAllProduct(){
        List<Product> products = productService.getAllBooks();
        return ResponseEntity.ok(products);
    }


}

