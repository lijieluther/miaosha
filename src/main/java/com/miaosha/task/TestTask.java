package com.miaosha.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author luther
 */
@Component
public class TestTask {
    private static final SimpleDateFormat dataFormat=new SimpleDateFormat("HH:mm:ss");

    //定义每过3秒执行下任务
    /**
     * @Scheduled(fixedRate = 3000)  http://cron.qqe2.com/(任务表达式)
     */
    @Scheduled(cron = "4-40 * * * * ? ")
    public void reportCurrentTime(){
        System.out.println("现在时间："+dataFormat.format(new Date()));
    }
}
