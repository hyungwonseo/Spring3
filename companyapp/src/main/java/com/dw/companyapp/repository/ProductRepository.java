package com.dw.companyapp.repository;

import com.dw.companyapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 과제 3-5 제품을 조회할 때 단가를 매개변수로 전달하고 해당 단가보다 싼 제품을 조회하는 API
    @Query("select p from Product p where p.unitPrice < :price")
    List<Product> getProductsBelowPrice(double price);

    // 과제 4-9 제품명의 일부를 매개변수로 해당 문자열을 포함하는 제품들을 조회하는 API
    List<Product> findByProductNameLike(String name);
}
