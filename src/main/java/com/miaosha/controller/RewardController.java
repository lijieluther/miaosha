package com.miaosha.controller;

import com.miaosha.common.Tool;
import com.miaosha.dataobject.QuaLiFiCationsDO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.response.CommonReturnType;
import com.miaosha.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("reward")
@RequestMapping("/reward")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class RewardController extends BaseController {
    @Autowired
    private RewardService rewardService;

    @RequestMapping("/getReward")
    @ResponseBody
    public CommonReturnType getReward(@RequestParam(name = "id") String id) throws BusinessException {
        if("".equals(Tool.toTrim(id))){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //调用service服务获取对应id用户对象并返回给前端
        QuaLiFiCationsDO quaLiFiCationsDO = rewardService.getUserById(Integer.parseInt(id));

        //若获取的对应用户信息不存在
        if (quaLiFiCationsDO == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        if(quaLiFiCationsDO.getQualificationsNum()<1){
            throw new BusinessException(EmBusinessError.NOTWHITE_ERROR);
        }
        //返回通用对象
        return CommonReturnType.create(quaLiFiCationsDO);
    }

    @RequestMapping("/winReward")
    @ResponseBody
    public CommonReturnType winReward(@RequestParam(name = "id") String id,@RequestParam(name = "userId") String userId) throws BusinessException {
        if("".equals(Tool.toTrim(id))){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        if("".equals(Tool.toTrim(userId))){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //调用service服务获取对应id用户对象并返回给前端
        QuaLiFiCationsDO quaLiFiCationsDO = rewardService.getUserById(Integer.parseInt(userId));

        //若获取的对应用户信息不存在
        if (quaLiFiCationsDO == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }

        if(quaLiFiCationsDO.getQualificationsNum()<1){
            throw new BusinessException(EmBusinessError.NOTWHITE_ERROR);
        }
        //进行抽奖
        String result=rewardService.getWinReward(Integer.parseInt(id),Integer.parseInt(userId));
        //返回通用对象
        return CommonReturnType.create(result);
    }
}
