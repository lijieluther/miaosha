package com.miaosha;

import com.miaosha.dao.UserDoMapper;
import com.miaosha.dataobject.UserDO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
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
/*@EnableScheduling*/
/**
 *
 * 开启异步调用方法
 *
 */
@EnableAsync
public class App 
{

    @Autowired
    private UserDoMapper userDOMapper;

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
        Logger logger = LogManager.getLogger(App.class);
        logger.info( "Hello World!!!" );
        SpringApplication.run(App.class,args);
    }
}
