package com.miaosha.dao;

import com.miaosha.dataobject.UserDO;

public interface UserDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(Integer id);

    UserDO selectByTelphone(String telPhone);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);
}