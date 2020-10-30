package com.wang.bean;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {MessageSender.LOG_QUEUE})
public class LogMessageReceiver {
   @RabbitHandler
    public void handleMessage(Employee employee) throws InterruptedException {
        Thread.sleep(1000*30);//睡30秒
        System.out.println("******日志队列消息接收：打印一条日志*************");
    }
}
