package com.miaosha.dao;

import com.miaosha.dataobject.RewardDO;

/**
 * @author luther
 */
public interface RewardDoMapper {
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
    int insert(RewardDO record);
    /**
     * 插入
     * @param record
     * @return Result<int>
     */
    int insertSelective(RewardDO record);
    /**
     * 查询
     * @param id
     * @return Result<RewardDO>
     */
    RewardDO selectByPrimaryKey(Integer id);
    /**
     * 更新
     * @param record
     * @return Result<int>
     */
    int updateByPrimaryKeySelective(RewardDO record);
    /**
     * 更新
     * @param record
     * @return Result<int>
     */
    int updateByPrimaryKey(RewardDO record);
}