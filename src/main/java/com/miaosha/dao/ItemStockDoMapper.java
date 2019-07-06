package com.miaosha.dao;

import com.miaosha.dataobject.ItemStockDO;

/**
 * @author luther
 */
public interface ItemStockDoMapper {
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
    int insert(ItemStockDO record);
    /**
     * 插入
     * @param record
     * @return Result<int>
     */
    int insertSelective(ItemStockDO record);
    /**
     * 查询
     * @param id
     * @return Result<ItemStockDO>
     */
    ItemStockDO selectByPrimaryKey(Integer id);
    /**
     * 更新
     * @param record
     * @return Result<int>
     */
    int updateByPrimaryKeySelective(ItemStockDO record);
    /**
     * 更新
     * @param record
     * @return Result<int>
     */
    int updateByPrimaryKey(ItemStockDO record);
}