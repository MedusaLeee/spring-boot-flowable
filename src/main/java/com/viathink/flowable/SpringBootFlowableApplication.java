package com.viathink.flowable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableWebMvc
@EnableAsync
public class SpringBootFlowableApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootFlowableApplication.class, args);
    }
}
