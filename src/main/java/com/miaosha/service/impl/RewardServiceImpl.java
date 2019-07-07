package com.miaosha.service.impl;

import com.miaosha.dao.QuaLiFiCationsDoMapper;
import com.miaosha.dao.RewardDoMapper;
import com.miaosha.dataobject.QuaLiFiCationsDO;
import com.miaosha.dataobject.RewardDO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * @author luther
 */
@Service
@EnableTransactionManagement
public class RewardServiceImpl implements RewardService {
    @Autowired
    private QuaLiFiCationsDoMapper quaLiFiCationsDOMapper;
    @Autowired
    private RewardDoMapper rewardDOMapper;
    @Override
    public QuaLiFiCationsDO getUserById(Integer id) {
        //调用userdomapper获取用户的dataobject
        QuaLiFiCationsDO quaLiFiCationsDO=quaLiFiCationsDOMapper.selectByPrimaryKey(id);
        if(quaLiFiCationsDO==null){
            return null;
        }
        return quaLiFiCationsDO;
    }

    /**
     * 判断奖品个数是否还可发放
     * @param id
     * @return
     */
    public RewardDO getRewardById(Integer id) {
        RewardDO rewardDO=rewardDOMapper.selectByPrimaryKey(id);
        if(rewardDO==null){
            return null;
        }
        return rewardDO;
    }
    /**
     * 获取礼品并减去资格
     * @param userId
     * @return
     */
    public void subQulifiCationById(Integer userId)  throws BusinessException {
        //调用userdomapper获取用户的dataobject
        QuaLiFiCationsDO quaLiFiCationsDO=quaLiFiCationsDOMapper.selectByPrimaryKey(userId);
        if(quaLiFiCationsDO==null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        Integer num=quaLiFiCationsDO.getQualificationsNum();
        QuaLiFiCationsDO quaLiFiCationsDO1=new QuaLiFiCationsDO();
        quaLiFiCationsDO1.setQualificationsNum(num-1);
        quaLiFiCationsDO1.setQualificationsId(userId);
        quaLiFiCationsDOMapper.updateByPrimaryKeySelective(quaLiFiCationsDO1);

    }
    /**
     * 奖品发放
     * @return
     */
    @Transactional
    @Override
    public String getWinReward(Integer id,Integer userId) {
        String result="";
        Random randomTool = new Random();
        Double userSelect = randomTool.nextDouble();
        DecimalFormat df = new DecimalFormat("0.00");
        String reWard=df.format(userSelect);
        if(Double.parseDouble(reWard)<Double.parseDouble(String.valueOf("0.05"))){
             result="1";
        }else  if(Double.parseDouble(reWard)<Double.parseDouble(String.valueOf("0.1"))){
             result="2";
        }else  if(Double.parseDouble(reWard)<Double.parseDouble(String.valueOf("0.3"))){
             result="3";
        }else{
             result="4";
        }
        //判断奖品是否已发送完
        RewardDO rewardDO=this.getRewardById(id);
        if(rewardDO==null){
            return result;
        }
        Integer first=rewardDO.getFirstAward();
        Integer second=rewardDO.getSecondAward();
        Integer third=rewardDO.getThirdAward();
        Integer four=rewardDO.getFourAward();
        RewardDO rewardDO1=new RewardDO();
        if(result.equals("1")){
            if(first>0){
                rewardDO1.setFirstAward(first-1);
                rewardDO1.setId(id);
                rewardDOMapper.updateByPrimaryKeySelective(rewardDO1);
                try {
                    subQulifiCationById(userId);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }else if(second>0){
                rewardDO1.setSecondAward(second-1);
                rewardDO1.setId(id);
                rewardDOMapper.updateByPrimaryKeySelective(rewardDO1);
                try {
                    subQulifiCationById(userId);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }else if(third>0){
                rewardDO1.setThirdAward(third-1);
                rewardDO1.setId(id);
                rewardDOMapper.updateByPrimaryKeySelective(rewardDO1);
                try {
                    subQulifiCationById(userId);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }else if(four>0){
                rewardDO1.setFourAward(four-1);
                rewardDO1.setId(id);
                rewardDOMapper.updateByPrimaryKeySelective(rewardDO1);
                try {
                    subQulifiCationById(userId);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }else{
                result="0";
            }
        }else if(result.equals("2")){
            if(second>0){
                rewardDO1.setSecondAward(second-1);
                rewardDO1.setId(id);
                rewardDOMapper.updateByPrimaryKeySelective(rewardDO1);
                try {
                    subQulifiCationById(userId);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }else if(third>0){
                rewardDO1.setThirdAward(third-1);
                rewardDO1.setId(id);
                rewardDOMapper.updateByPrimaryKeySelective(rewardDO1);
                try {
                    subQulifiCationById(userId);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }else if(four>0){
                rewardDO1.setFourAward(four-1);
                rewardDO1.setId(id);
                rewardDOMapper.updateByPrimaryKeySelective(rewardDO1);
                try {
                    subQulifiCationById(userId);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }else if(first>0){
                rewardDO1.setFirstAward(first-1);
                rewardDO1.setId(id);
                rewardDOMapper.updateByPrimaryKeySelective(rewardDO1);
                try {
                    subQulifiCationById(userId);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }else{
                result="0";
            }
        }else if(result.equals("3")){
            if(third>0){
                rewardDO1.setThirdAward(third-1);
                rewardDO1.setId(id);
                rewardDOMapper.updateByPrimaryKeySelective(rewardDO1);
                try {
                    subQulifiCationById(userId);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }else if(four>0){
                rewardDO1.setFourAward(four-1);
                rewardDO1.setId(id);
                rewardDOMapper.updateByPrimaryKeySelective(rewardDO1);
                try {
                    subQulifiCationById(userId);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }else if(first>0){
                rewardDO1.setFirstAward(first-1);
                rewardDO1.setId(id);
                rewardDOMapper.updateByPrimaryKeySelective(rewardDO1);
                try {
                    subQulifiCationById(userId);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }else if(second>0){
                rewardDO1.setSecondAward(second-1);
                rewardDO1.setId(id);
                rewardDOMapper.updateByPrimaryKeySelective(rewardDO1);
                try {
                    subQulifiCationById(userId);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }else{
                result="0";
            }
        }else{
            if(four>0){
                rewardDO1.setFourAward(four-1);
                rewardDO1.setId(id);
                rewardDOMapper.updateByPrimaryKeySelective(rewardDO1);
                try {
                    subQulifiCationById(userId);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }else if(first>0){
                rewardDO1.setFirstAward(first-1);
                rewardDO1.setId(id);
                rewardDOMapper.updateByPrimaryKeySelective(rewardDO1);
                try {
                    subQulifiCationById(userId);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }else if(second>0){
                rewardDO1.setSecondAward(second-1);
                rewardDO1.setId(id);
                rewardDOMapper.updateByPrimaryKeySelective(rewardDO1);
                try {
                    subQulifiCationById(userId);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }else if(third>0){
                rewardDO1.setThirdAward(third-1);
                rewardDO1.setId(id);
                rewardDOMapper.updateByPrimaryKeySelective(rewardDO1);
                try {
                    subQulifiCationById(userId);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }else{
                result="0";
            }
        }
        return result;
    }
}
