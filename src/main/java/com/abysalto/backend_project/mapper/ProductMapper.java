package com.abysalto.backend_project.mapper;

import com.abysalto.backend_project.product.dto.ProductDetailDTO;
import com.abysalto.backend_project.product.dto.ProductListDTO;
import com.abysalto.backend_project.product.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    
    public ProductListDTO toListDTO(Product product) {
        ProductListDTO dto = new ProductListDTO();
        dto.setThumbnail(product.getThumbnail());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        String description = product.getDescription();
        dto.setShortDescription(description.length() > 100 ? description.substring(0, 100) : description);
        return dto;
    }
    
    public ProductDetailDTO toDetailDTO(Product product) {
        ProductDetailDTO dto = new ProductDetailDTO();
        dto.setDescription(product.getDescription());
        return dto;
    }
}
