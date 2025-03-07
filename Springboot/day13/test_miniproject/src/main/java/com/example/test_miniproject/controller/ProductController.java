package com.example.test_miniproject.controller;

import com.example.test_miniproject.model.PageResponse;
import com.example.test_miniproject.model.Product;
import com.example.test_miniproject.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@Controller

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    //Lấy danh sách products
    @GetMapping("/") //http://localhost:8080
    public String getAllProduct(Model model, @RequestParam(required = false, defaultValue = "1") int page) {
        List<Product> products = productService.getAllProducts(); // lấy danh sách sản phầm

        // Tính giá giảm cho mỗi sản phẩm rating dưới 3
        for (Product product : products) {
            if (product.getRating() < 3) {
                double discountRate = 0.15;  // giảm giá 15%
                double priceDiscount = product.getPrice() * (1 - discountRate); // Tính giá giảm
                product.setPriceDiscount((int) priceDiscount);  // Gán giá giảm vào sản phẩm
            }else {
                product.setPriceDiscount(0);
            }
        }
        model.addAttribute("products", products);
        return "product-list"; // Tên template Thymeleaf
    }

    //Lấy chi tiết sản phẩm theo id
    @GetMapping("/products/{id}")
    public String getProductById(Model model, @PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "product-detail";
        } else {
            return "error";
        }
    }
}

