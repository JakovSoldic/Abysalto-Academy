package com.abysalto.backend_project.DummyJSON;

import com.abysalto.backend_project.DummyJSON.dto.DummyJsonProductDTO;
import com.abysalto.backend_project.DummyJSON.dto.DummyJsonResponseDTO;
import com.abysalto.backend_project.product.entity.Product;
import com.abysalto.backend_project.product.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class DummyJsonClient {
    
    private final ProductRepository productRepository;
    private final RestClient restClient;
    
    public DummyJsonClient(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.restClient = RestClient.create("https://dummyjson.com");
    }
    
    @PostConstruct
    public void fetchData() {
        DummyJsonResponseDTO response = restClient.get()
                                                  .uri("/products?limit=0")
                                                  .retrieve()
                                                  .body(DummyJsonResponseDTO.class);
        
        if (response != null && response.getProducts() != null) {
            List<Product> products = response.getProducts()
                                             .stream()
                                             .map(this::mapToEntity)
                                             .toList();
            
            productRepository.saveAll(products);
        }
    }
    
    private Product mapToEntity(DummyJsonProductDTO dto) {
        Product product = new Product();
        //product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
        product.setBrand(dto.getBrand());
        product.setRating(dto.getRating());
        product.setStock(dto.getStock());
        product.setAvailabilityStatus(dto.getAvailabilityStatus());
        product.setDiscountPercentage(dto.getDiscountPercentage());
        product.setThumbnail(dto.getThumbnail());
        product.setReturnPolicy(dto.getReturnPolicy());
        product.setShippingInformation(dto.getShippingInformation());
        product.setImages(String.join(",", dto.getImages()));
        return product;
    }
}
