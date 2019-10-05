package com.miaosha.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 自动启动浏览器的类
 * @author luther
 */
@Component
public class StepExecutorConfig  implements ApplicationRunner {
    private static final Logger logger = LogManager.getLogger(StepExecutorConfig.class);
    @Value("http://localhost/user/userList")
    private String loginUrl;

    @Value("${spring.web.googleexcute}")
    private String googleExcutePath;

    @Value("${spring.web.isopenurl}")
    private boolean isOpen;

    public StepExecutorConfig() {
        this.loginUrl = loginUrl;
        this.googleExcutePath = googleExcutePath;
        this.isOpen = isOpen;
    }

    @Override
    public void run(ApplicationArguments args){
        //先判断当前环境
        if (!exitisVersion()){
            //启动swagger-ui
            application();
        }
    }

    private Boolean exitisVersion() {
        String osName = System.getProperties().getProperty("os.name");
        if(osName.equals("Linux")) {
            logger.info("running in Linux");
            return true;
        }
        else{
            logger.info("don't running in Linux");
            return false;
        }
    }

    private void application() {

        if (isOpen){
            String cmd = googleExcutePath +" "+ loginUrl;
            logger.info("浏览地址：" + cmd);
            Runtime run = Runtime.getRuntime();
            try{
                run.exec(cmd);
                logger.info("启动浏览器打开项目成功");
            }catch (Exception e){
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
    }
}
