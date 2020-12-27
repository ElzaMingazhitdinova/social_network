package com.example.testingrestdocs.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig{

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:postgresql://localhost:5433/social_network");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("metyab20");
        return dataSourceBuilder.build();
    }


}
