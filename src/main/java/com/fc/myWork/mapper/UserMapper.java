package com.fc.myWork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.myWork.model.Entity.User;


/**
* @author administered
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2024-03-18 17:59:49
* @Entity User
*/
public interface UserMapper extends BaseMapper<User> {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
