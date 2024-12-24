package com.dw.jdbcapp.repository.iface;

import com.dw.jdbcapp.dto.ProductDTO;
import com.dw.jdbcapp.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();
    Product getProductById(int productNumber);
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    int deleteProduct(int id);
    List<Product> getProductsBelowPrice(double price);
    // 과제 4-8 제품번호와 재고를 매개변수로 해당 제품의 재고를 수정하는 API
    int updateProductWithStock(int id, int stock);
    // 과제 4-9 제품명의 일부를 매개변수로 해당 문자열을 포함하는 제품들을 조회하는 API
    List<Product> getProductByProductName(String name);
}
