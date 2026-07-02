package com.abysalto.backend_project.DummyJSON.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class DummyJsonProductDTO {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private String category;
    private String brand;
    private Double rating;
    private Integer stock;
    private String availabilityStatus;
    private Double discountPercentage;
    private String thumbnail;
    private String returnPolicy;
    private String shippingInformation;
    private List<String> images;
}
