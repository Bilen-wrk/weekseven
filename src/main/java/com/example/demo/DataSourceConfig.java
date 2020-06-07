package com.example.demo;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource getDataSource(){
        DataSourceBuilder dsb= DataSourceBuilder.create();
        dsb.driverClassName("org.h2.Driver");
        dsb.url("jdbc:h2:mem:testdb");
        dsb.username("SA");
        dsb.password("");
        return dsb.build();
    }
}
