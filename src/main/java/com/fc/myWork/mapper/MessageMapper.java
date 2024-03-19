package com.fc.myWork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.myWork.model.Entity.Message;


/**
* @author administered
* @description 针对表【message】的数据库操作Mapper
* @createDate 2024-03-18 11:50:00
* @Entity Message
*/
public interface MessageMapper extends BaseMapper<Message> {

    int deleteByPrimaryKey(Long id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

}
