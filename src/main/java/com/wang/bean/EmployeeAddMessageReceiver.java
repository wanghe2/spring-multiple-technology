package com.wang.bean;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {MessageSender.EMPLOYEE_ADD_QUEUE})
public class EmployeeAddMessageReceiver {

    @RabbitHandler
    public void handleMessage(String username, Channel channel) throws InterruptedException {
        Thread.sleep(1000*60);
        System.out.println("------------雇工新增队列接收：新增了一名雇工:"+username+"---------------");
    }

}
