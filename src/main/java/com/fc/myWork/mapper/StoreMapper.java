package com.fc.myWork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.myWork.model.Entity.Store;


/**
* @author administered
* @description 针对表【store】的数据库操作Mapper
* @createDate 2024-03-18 11:50:00
* @Entity Store
*/
public interface StoreMapper extends BaseMapper<Store> {

    int deleteByPrimaryKey(Long id);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);

}
