package com.fc.myWork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.myWork.model.Entity.Product;


/**
* @author administered
* @description 针对表【product】的数据库操作Mapper
* @createDate 2024-03-18 11:50:00
* @Entity Product
*/
public interface ProductMapper extends BaseMapper<Product> {

    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

}
