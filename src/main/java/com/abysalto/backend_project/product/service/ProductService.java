package com.abysalto.backend_project.product.service;

import com.abysalto.backend_project.mapper.ProductMapper;
import com.abysalto.backend_project.product.dto.ProductDetailDTO;
import com.abysalto.backend_project.product.dto.ProductListDTO;
import com.abysalto.backend_project.product.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
    
    @Cacheable(value = "products")
    public List<ProductListDTO> getAllProducts() {
        log.info("Fetching all products");
        return productRepository.findAll().stream().map(productMapper::toListDTO).toList();
    }
    
    @Cacheable(value = "product")
    public ProductDetailDTO getProductById(Long productId) {
        log.info("Fetching product with id: {}", productId);
        return productMapper.toDetailDTO(productRepository.findById(productId).orElseThrow(() -> {
            log.error("Product not found with id: {}", productId);
            return new RuntimeException("Product not found: " + productId);
        }));
    }
    
    @Cacheable(value = "products-filter", key = "#category + '-' + #price")
    public List<ProductListDTO> filterProducts(String category,
                                               BigDecimal price) {
        
        log.info("Filtering products - category: {}, price: {}", category, price);
        if (category != null && price != null) {
            return productRepository.findByCategoryAndPrice(category, price).stream().map(productMapper::toListDTO).toList();                                 
        } else if (category != null) {
            return productRepository.findByCategory(category).stream().map(productMapper::toListDTO).toList();
        } else if (price != null) {
            return productRepository.findByPrice(price).stream().map(productMapper::toListDTO).toList();
        } else {
            return getAllProducts();
        }
        
    }
    
    @Cacheable(value = "products-search", key = "#title")
    public List<ProductListDTO> getByTitle(String title) {
        log.info("Searching products by title: {}", title);
        return productRepository.findByTitleContainingIgnoreCase(title).stream().map(productMapper::toListDTO).toList();
    }
}
