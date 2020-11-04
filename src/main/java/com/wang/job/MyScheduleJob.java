package com.wang.job;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;

@Component
public class MyScheduleJob {

    private Logger logger = LoggerFactory.getLogger(MyScheduleJob.class);

    @Scheduled(cron = "0 * */5 * * *")
    public void doJob(){
        logger.info("每5秒执行一次");
    }
}
