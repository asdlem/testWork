package com.fc.myWork.model.Entity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * 台账模块实体类
 */
@Data
@TableName(value = "accounting")
public class Accounting {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 商品ID，外键关联商品表
     */
    private Long productId;
    /**
     * 门店ID，外键关联门店表
     */
    private Long storeId;
    /**
     * 交易类型，IN表示入库，OUT表示出库
     */
    private String type;
    /**
     * 交易数量
     */
    private Integer quantity;
    /**
     * 交易时间，默认为当前时间戳
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime transactionTime;
}