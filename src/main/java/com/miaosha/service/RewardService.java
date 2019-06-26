package com.miaosha.service;

import com.miaosha.dataobject.QuaLiFiCationsDO;

public interface RewardService {
    //通过用户ID获取用户对象的方法
    QuaLiFiCationsDO getUserById(Integer id);
    //获取几等奖
    public String getWinReward(Integer id,Integer userId);
}
