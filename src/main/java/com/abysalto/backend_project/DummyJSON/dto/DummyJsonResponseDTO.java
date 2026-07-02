package com.abysalto.backend_project.DummyJSON.dto;

import java.util.List;
import lombok.Data;

@Data
public class DummyJsonResponseDTO {
    private List<DummyJsonProductDTO> products;
    private Integer total;
    private Integer skip;
    private Integer limit;
}