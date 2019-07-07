package com.miaosha.dao;

import com.miaosha.dataobject.ItemDO;

import java.util.List;

/**
 * @author luther
 */
public interface ItemDoMapper {
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
    int insert(ItemDO record);
    /**
     * 插入
     * @param record
     * @return Result<int>
     */
    int insertSelective(ItemDO record);
    /**
     * 查询
     * @param id
     * @return Result<ItemDo>
     */
    ItemDO selectByPrimaryKey(Integer id);
    /**
     * 查询
     * @return Result<ItemDo>
     */
    List<ItemDO> listItem();
    /**
     * 更新
     * @param record
     * @return Result<int>
     */
    int updateByPrimaryKeySelective(ItemDO record);
    /**
     * 更新
     * @param record
     * @return Result<int>
     */
    int updateByPrimaryKey(ItemDO record);
}