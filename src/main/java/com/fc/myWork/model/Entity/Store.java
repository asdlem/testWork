package com.fc.myWork.model.Entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * 门店管理模块实体类
 */
@Data
@TableName(value = "store")
public class Store {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 门店名称
     */
    private String name;
    /**
     * 门店地址
     */
    private String address;
    /**
     * 门店电话
     */
    private String phone;
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