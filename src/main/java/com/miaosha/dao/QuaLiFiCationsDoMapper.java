package com.miaosha.dao;

import com.miaosha.dataobject.QuaLiFiCationsDO;

/**
 * @author luther
 */
public interface QuaLiFiCationsDoMapper {
    /**
     * 删除
     * @param qualificationsId
     * @return Result<int>
     */
    int deleteByPrimaryKey(Integer qualificationsId);
    /**
     * 插入
     * @param record
     * @return Result<int>
     */
    int insert(QuaLiFiCationsDO record);
    /**
     * 插入
     * @param record
     * @return Result<int>
     */
    int insertSelective(QuaLiFiCationsDO record);
    /**
     * 查询
     * @param qualificationsId
     * @return Result<int>
     */
    QuaLiFiCationsDO selectByPrimaryKey(Integer qualificationsId);
    /**
     * 更新
     * @param record
     * @return Result<int>
     */
    int updateByPrimaryKeySelective(QuaLiFiCationsDO record);
    /**
     * 更新
     * @param record
     * @return Result<int>
     */
    int updateByPrimaryKey(QuaLiFiCationsDO record);
}