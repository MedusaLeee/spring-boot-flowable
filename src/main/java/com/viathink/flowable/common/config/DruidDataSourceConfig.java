package com.viathink.flowable.common.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DruidDataSourceConfig {
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource flowableDataSource(){
        return DruidDataSourceBuilder.create().build();
    }
}
