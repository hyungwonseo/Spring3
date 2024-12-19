package com.dw.jdbcapp.service;

import com.dw.jdbcapp.exception.InvalidRequestException;
import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.repository.iface.ProductRepository;
import com.dw.jdbcapp.repository.jdbc.ProductJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    @Qualifier("productTemplateRepository")
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Product getProductById(int productNumber) {
        if (productNumber < 0) {
            throw new InvalidRequestException("존재하지 않는 제품번호: "
                    + productNumber);
        }
        return productRepository.getProductById(productNumber);
    }

    public Product saveProduct(Product product) {
        return productRepository.saveProduct(product);
    }

    public List<Product> saveProductList(List<Product> productList) {
        for (Product data : productList) {
            productRepository.saveProduct(data);
        }
        return productList;
    }

    public Product updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }

    public int deleteProduct(int id) {
        return productRepository.deleteProduct(id);
    }

    // 과제 3-5 해당 단가보다 싼 제품이 없을 경우, "해당되는 제품이 없습니다"를 출력하는 예외처리
    public List<Product> getProductsBelowPrice(double price) {
        List<Product> products = productRepository.getProductsBelowPrice(price);
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("해당되는 제품이 없습니다: " +
                    price);
        }
        return products;
    }
}
