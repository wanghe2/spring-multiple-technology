package com.wang.bean;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    public final static String EMPLOYEE_ADD_QUEUE = "employee_add_queue";
    public final static String LOG_QUEUE = "log_queue";
    public final static String EMPLOYEE_COUNT_STAT_QUEUE = "employee_count_stat_queue";
    public final static String EMPLOYEE_FANOUT_EXCHANGE = "employee_fanout_exchange";

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void employeeAddSend(Employee employee) {
        amqpTemplate.convertSendAndReceive(EMPLOYEE_ADD_QUEUE,employee.getUsername());//直接发送到add队列
        amqpTemplate.convertAndSend(EMPLOYEE_FANOUT_EXCHANGE,"",employee);//广播（log队列和stat队列都会收到）
        System.out.println("==========消息发送完毕==============");
    }

}
