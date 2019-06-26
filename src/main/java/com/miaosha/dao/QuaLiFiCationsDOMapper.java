package com.miaosha.dao;

import com.miaosha.dataobject.QuaLiFiCationsDO;

public interface QuaLiFiCationsDOMapper {
    int deleteByPrimaryKey(Integer qualificationsId);

    int insert(QuaLiFiCationsDO record);

    int insertSelective(QuaLiFiCationsDO record);

    QuaLiFiCationsDO selectByPrimaryKey(Integer qualificationsId);

    int updateByPrimaryKeySelective(QuaLiFiCationsDO record);

    int updateByPrimaryKey(QuaLiFiCationsDO record);
}