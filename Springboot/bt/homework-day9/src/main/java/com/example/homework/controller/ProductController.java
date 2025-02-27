package com.example.homework.controller;

import com.example.homework.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    private List<Product> products = new ArrayList<>(List.of(
            new Product("121", "MacBook Pro 16", "Powerful Apple laptop with M2 Pro", 2499, "Apple"),
            new Product("122", "Dell XPS 15", "Premium Ultrabook with 4K display", 1999, "Dell"),
            new Product("123", "Lenovo ThinkPad X1 Carbon", "Business laptop with strong security", 1799, "Lenovo"),
            new Product("124", "Asus ROG Zephyrus G15", "Gaming laptop with RTX 3070", 1899, "Asus"),
            new Product("P004", "Dell XPS 13", "Premium Ultrabook laptop", 1299, "Dell")
    ));

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return products;
    }
}
