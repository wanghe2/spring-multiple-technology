package com.wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.reflect.Proxy;

@SpringBootApplication
@EnableScheduling
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan(basePackages={"com.wang"})
public class SpringSwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSwaggerApplication.class, args);
    }

}
