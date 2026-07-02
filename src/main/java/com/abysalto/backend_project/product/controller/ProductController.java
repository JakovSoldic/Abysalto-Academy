package com.abysalto.backend_project.product.controller;

import com.abysalto.backend_project.product.dto.ProductDetailDTO;
import com.abysalto.backend_project.product.dto.ProductListDTO;
import com.abysalto.backend_project.product.service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping
    public ResponseEntity<List<ProductListDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailDTO> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductListDTO>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(productService.getByTitle(title));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductListDTO>> filterProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal price) {
        return ResponseEntity.ok(productService.filterProducts(category, price));
    }
    
}
