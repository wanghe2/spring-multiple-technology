package com.wang.config;

import com.wang.bean.MessageSender;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfiguration {

    @Bean
    Queue employee_add_queue(){
        return new Queue(MessageSender.EMPLOYEE_ADD_QUEUE);
    }

    @Bean
    Queue log_queue() {
        return new Queue(MessageSender.LOG_QUEUE);
    }

    @Bean
    Queue employee_count_stat_queue(){
        return new Queue(MessageSender.EMPLOYEE_COUNT_STAT_QUEUE);
    }

    @Bean
    FanoutExchange employee_fanout_exchange(){
        return new FanoutExchange(MessageSender.EMPLOYEE_FANOUT_EXCHANGE);
    }

    @Bean
    Binding log_binding(){
        return BindingBuilder.bind(log_queue()).to(employee_fanout_exchange());
    }

    @Bean
    Binding employee_stat_binding(Queue employee_count_stat_queue,FanoutExchange employee_fanout_exchange){
        return BindingBuilder.bind(employee_count_stat_queue).to(employee_fanout_exchange);
    }
}
