package com.miaosha.dao;

import com.miaosha.dataobject.UserPasswordDO;

/**
 * @author luther
 */
public interface UserPasswordDoMapper {
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
    int insert(UserPasswordDO record);
    /**
     * 插入
     * @param record
     * @return Result<int>
     */
    int insertSelective(UserPasswordDO record);
    /**
     * 查询
     * @param id
     * @return Result<UserPasswordDO>
     */
    UserPasswordDO selectByPrimaryKey(Integer id);
    /**
     * 查询
     * @param id
     * @return Result<UserPasswordDO>
     */
    UserPasswordDO selectByUserId(Integer id);
    /**
     * 插入
     * @param record
     * @return Result<int>
     */
    int updateByPrimaryKeySelective(UserPasswordDO record);
    /**
     * 插入
     * @param record
     * @return Result<int>
     */
    int updateByPrimaryKey(UserPasswordDO record);
}