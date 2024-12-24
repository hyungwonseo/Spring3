package com.dw.jdbcapp.service;

import com.dw.jdbcapp.dto.ProductDTO;
import com.dw.jdbcapp.exception.InvalidRequestException;
import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.repository.iface.ProductRepository;
import com.dw.jdbcapp.repository.jdbc.ProductJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    // 과제 4-8 제품번호와 재고를 매개변수로 해당 제품의 재고를 수정하는 API
    public String updateProductWithStock(int id, int stock) {
        productRepository.updateProductWithStock(id, stock);
        return "성공적으로 수정하였습니다";
    }

    // 과제 4-9 제품명의 일부를 매개변수로 해당 문자열을 포함하는 제품들을 조회하는 API
    public List<Product> getProductByProductName(String name) {
        return productRepository.getProductByProductName(name);
    }

    // 과제 4-10 ProductDTO를 아래 형식으로 추가하고 조회하는 API
    public List<ProductDTO> getProductsByStockValue() {
        List<Product> products = productRepository.getAllProducts();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product data : products) {
            productDTOList.add(ProductDTO.fromProduct(data));
            //productDTOList.add(new ProductDTO(data));
        }
        return productDTOList;
    }
}
