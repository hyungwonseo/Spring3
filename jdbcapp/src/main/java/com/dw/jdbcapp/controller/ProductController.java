package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.service.CustomerService;
import com.dw.jdbcapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/find-all-products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // 과제 1-1 제품번호를 기준으로 제품 정보를 조회하는 API
    @GetMapping("/products/{productNumber}")
    public Product getProductById(@PathVariable int productNumber) {
        return productService.getProductById(productNumber);
    }
}
