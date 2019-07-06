package com.miaosha.controller;

import com.miaosha.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author luther
 */
public class RedisController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("/redis")
    public CommonReturnType redisJsonResult(){
        stringRedisTemplate.opsForValue().set("hello","nihao");
        return CommonReturnType.create(stringRedisTemplate.opsForValue().get("hello"));
    }
}
