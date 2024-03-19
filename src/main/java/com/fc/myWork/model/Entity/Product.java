package com.fc.myWork.model.Entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * 商品模块实体类
 */
@Data
@TableName(value = "product")
public class Product {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品规格
     */
    private String specification;
    /**
     * 商品单价
     */
    private BigDecimal price;
    /**
     * 商品库存，默认为0
     */
    private Integer stock;
    /**
     * 创建时间，默认为当前时间戳
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    /**
     * 更新时间，每次更新时自动设置为当前时间戳
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

}