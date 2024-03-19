package com.fc.myWork.model.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class ProductDto {
    private Long id;
    private String name;
    private String specification;
    private BigDecimal price;
    private Integer stock;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}