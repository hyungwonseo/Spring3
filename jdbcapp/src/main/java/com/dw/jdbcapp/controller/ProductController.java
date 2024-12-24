package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.dto.ProductDTO;
import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.service.CustomerService;
import com.dw.jdbcapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    // 과제 3-5 제품을 조회할 때 단가를 매개변수로 전달하고 해당 단가보다 싼 제품을 조회하는 API
    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProductsBelowPrice(
            @RequestParam double price_below) {
        return new ResponseEntity<>(
                productService.getProductsBelowPrice(price_below),
                HttpStatus.OK);
    }

    // 과제 4-8 제품번호와 재고를 매개변수로 해당 제품의 재고를 수정하는 API
    @PutMapping("/products/update")
    public ResponseEntity<String> updateProductWithStock(
            @RequestParam int id, @RequestParam int stock) {
        return new ResponseEntity<>(
                productService.updateProductWithStock(id, stock),
                HttpStatus.OK);
    }

    // 과제 4-9 제품명의 일부를 매개변수로 해당 문자열을 포함하는 제품들을 조회하는 API
    @GetMapping("/products/name/{name}")
    public ResponseEntity<List<Product>> getProductByProductName(
            @PathVariable String name) {
        return new ResponseEntity<>(
                productService.getProductByProductName(name),
                HttpStatus.OK);
    }

    // 과제 4-10 ProductDTO를 아래 형식으로 추가하고 조회하는 API
    @GetMapping("/products/stockvalue")
    public ResponseEntity<List<ProductDTO>> getProductsByStockValue() {
        return new ResponseEntity<>(
                productService.getProductsByStockValue(),
                HttpStatus.OK);
    }
}
