package com.fc.myWork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.myWork.model.Entity.Accounting;
import com.mchange.util.Base64Encoder;


/**
* @author administered
* @description 针对表【accounting】的数据库操作Mapper
* @createDate 2024-03-18 11:50:00
* @Entity Accounting
*/
public interface AccountingMapper extends BaseMapper<Accounting> {

    int deleteByPrimaryKey(Long id);

    int insert(Accounting record);

    int insertSelective(Accounting record);

    Accounting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Accounting record);

    int updateByPrimaryKey(Accounting record);

}
