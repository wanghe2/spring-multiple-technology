package com.wang.bean;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@RabbitListener(queues = {MessageSender.EMPLOYEE_COUNT_STAT_QUEUE})
public class EmployeeStatMessageReceiver {
    private static AtomicInteger add_total_count = new AtomicInteger(0);

    @RabbitHandler
    public void handleMessage(Employee employee) throws InterruptedException {
        Thread.sleep(1000*30);
        int count = add_total_count.incrementAndGet();
        System.out.println("***********统计队列消息接收，累计新增人数为****"+count+"********************");
    }

}
