package com.fc.myWork.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPropertiesToUpdate {
    private BigDecimal price; // 商品价格
    private String description; // 商品描述
    private Integer stock; // 商品库存

}