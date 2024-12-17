package com.dw.jdbcapp.repository.template;

import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.repository.iface.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductTemplateRepository implements ProductRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Product> productRowMapper
            = new RowMapper<Product>() {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setProductId(rs.getInt("제품번호"));
            product.setProductName(rs.getString("제품명"));
            product.setPackageUnit(rs.getString("포장단위"));
            product.setUnitPrice(rs.getInt("단가"));
            product.setStock(rs.getInt("재고"));
            return product;
        }
    };

    @Override
    public List<Product> getAllProducts() {
        String query = "select * from 제품";
        return jdbcTemplate.query(query, productRowMapper);
    }

    @Override
    public Product getProductById(int productNumber) {
        String query = "select * from 제품 where 제품번호=?";
        return jdbcTemplate.queryForObject(query, productRowMapper,
                productNumber);
    }

    @Override
    public Product saveProduct(Product product) {
        String query = "insert into 제품(제품번호,제품명,포장단위,단가,재고)" +
                " values(?,?,?,?,?)";
        jdbcTemplate.update(query,
                product.getProductId(),
                product.getProductName(),
                product.getPackageUnit(),
                product.getUnitPrice(),
                product.getStock());
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public int deleteProduct(int id) {
        return 0;
    }
}
