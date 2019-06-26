package com.miaosha.dao;

import com.miaosha.dataobject.RewardDO;

public interface RewardDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RewardDO record);

    int insertSelective(RewardDO record);

    RewardDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RewardDO record);

    int updateByPrimaryKey(RewardDO record);
}