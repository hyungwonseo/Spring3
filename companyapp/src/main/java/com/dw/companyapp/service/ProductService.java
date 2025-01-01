package com.dw.companyapp.service;

import com.dw.companyapp.dto.ProductDTO;
import com.dw.companyapp.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public List<Product> getAllProducts() {
        return null;
    }

    // 과제 1-1 제품번호를 기준으로 제품 정보를 조회하는 API
    public Product getProductById(int productNumber) {
        return null;
    }

    // 과제 2-1 제품테이블에 새로운 제품 1개를 추가하는 API
    public Product saveProduct(Product product) {
        return null;
    }

    // 과제 2-2 제품테이블에 여러 제품을 추가하는 API
    public List<Product> saveProductList(List<Product> productList) {
        return null;
    }

    // 과제 2-4 제품테이블의 정보를 수정하는 API
    public Product updateProduct(Product product) {
        return null;
    }

    // 과제 2-5 제품테이블의 정보를 삭제하는 API
    public int deleteProduct(int id) {
        return 0;
    }

    // 과제 3-5 제품을 조회할 때 단가를 매개변수로 전달하고 해당 단가보다 싼 제품을 조회하는 API
    // 해당 단가보다 싼 제품이 없을 경우, "해당되는 제품이 없습니다"를 출력하는 예외처리
    public List<Product> getProductsBelowPrice(double price) {
        return null;
    }

    // 과제 4-8 제품번호와 재고를 매개변수로 해당 제품의 재고를 수정하는 API
    public String updateProductWithStock(int id, int stock) {
        return null;
    }

    // 과제 4-9 제품명의 일부를 매개변수로 해당 문자열을 포함하는 제품들을 조회하는 API
    public List<Product> getProductByProductName(String name) {
        return null;
    }

    // 과제 4-10 ProductDTO를 아래 형식으로 추가하고 조회하는 API
    public List<ProductDTO> getProductsByStockValue() {
        return null;
    }
}
