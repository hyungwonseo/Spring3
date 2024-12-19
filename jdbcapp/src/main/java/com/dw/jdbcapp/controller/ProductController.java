package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.service.CustomerService;
import com.dw.jdbcapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/find-all-products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(
                productService.getAllProducts(),
                HttpStatus.OK);
    }

    // 과제 1-1 제품번호를 기준으로 제품 정보를 조회하는 API
    @GetMapping("/products/{productNumber}")
    public ResponseEntity<Product> getProductById(@PathVariable int productNumber) {
        return new ResponseEntity<>(
                productService.getProductById(productNumber),
                HttpStatus.OK);
    }

    // 과제 2-1 제품테이블에 새로운 제품 1개를 추가하는 API
    @PostMapping("/post/product")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return new ResponseEntity<>(
                productService.saveProduct(product),
                HttpStatus.CREATED);
    }

    // 과제 2-2 제품테이블에 여러 제품을 추가하는 API
    @PostMapping("/post/productlist")
    public ResponseEntity<List<Product>> saveProductList(
            @RequestBody List<Product> productList) {
        return new ResponseEntity<>(
                productService.saveProductList(productList),
                HttpStatus.CREATED);
    }

    // 과제 2-4 제품테이블의 정보를 수정하는 API
    @PutMapping("/put/product")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(
                productService.updateProduct(product),
                HttpStatus.OK);
    }

    // 과제 2-5 제품테이블의 정보를 삭제하는 API
    @DeleteMapping("/delete/product")
    public ResponseEntity<String> deleteProduct(@RequestParam int id) {
        return new ResponseEntity<>(
                "제품번호: " + productService.deleteProduct(id)
                        + " 삭제됨",
                HttpStatus.OK);
    }
}
