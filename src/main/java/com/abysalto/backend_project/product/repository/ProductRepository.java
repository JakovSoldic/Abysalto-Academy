package com.abysalto.backend_project.product.repository;

import com.abysalto.backend_project.product.entity.Product;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    
    List<Product> findByTitleContainingIgnoreCase(String title);
    
    List<Product> findByCategory(String category);
    
    List<Product> findByPrice(BigDecimal price);
    
    List<Product> findByCategoryAndPrice(String category, BigDecimal price);
    
}