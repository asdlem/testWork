package com.fc.myWork.model.Entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * 消息模块实体类
 */
@Data
@TableName(value = "message")
public class Message {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 关联的商品ID
     */
    private Long productId;
    /**
     * 消息发送时间
     */
    private LocalDateTime outTime;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 阅读状态，默认为未读（false）
     */
    private Boolean readStatus;
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