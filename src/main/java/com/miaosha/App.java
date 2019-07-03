package com.miaosha;

import com.miaosha.dao.UserDOMapper;
import com.miaosha.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = {"com.miaosha"})
@RestController
//扫描mybtis mapper包路径
@MapperScan(basePackages = "com.miaosha.dao")
//扫描需要的包，包括一些工具类
@ComponentScan(basePackages = "com.miaosha.*")
//开启定时任务
@EnableScheduling
//开启异步调用方法
@EnableAsync
public class App 
{
    @Autowired
    private UserDOMapper userDOMapper;

    @RequestMapping("/")
    public String home(){
        UserDO userDO=userDOMapper.selectByPrimaryKey(1);
        if(userDO==null){
            return "用户不存在！";
        }else{
            return userDO.getName();
        }
    }
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );
        SpringApplication.run(App.class,args);
    }
}
