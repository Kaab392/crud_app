package com.kaab.crud_app.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class AddressDatabaseConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.address")
    public DataSourceProperties addressDataSourceProperties(){
        return new DataSourceProperties();
    }
    @Bean
    public DataSource addressDataSource(){
        return addressDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
    @Bean
    public JdbcTemplate addressJdbcTemplate(@Qualifier("addressDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
