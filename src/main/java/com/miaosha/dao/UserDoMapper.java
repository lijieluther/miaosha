package com.miaosha.dao;

import com.miaosha.dataobject.UserDO;

/**
 * @author luther
 */
public interface UserDoMapper {
    /**
     * 删除
     * @param id
     * @return Result<int>
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 插入
     * @param record
     * @return Result<int>
     */
    int insert(UserDO record);
    /**
     * 插入
     * @param record
     * @return Result<int>
     */
    int insertSelective(UserDO record);
    /**
     * 查询
     * @param id
     * @return Result<UserDO>
     */
    UserDO selectByPrimaryKey(Integer id);
    /**
     * 查询
     * @param telPhone
     * @return Result<int>
     */
    UserDO selectByTelphone(String telPhone);
    /**
     * 更新
     * @param record
     * @return Result<int>
     */
    int updateByPrimaryKeySelective(UserDO record);
    /**
     * 更新
     * @param record
     * @return Result<int>
     */
    int updateByPrimaryKey(UserDO record);
}