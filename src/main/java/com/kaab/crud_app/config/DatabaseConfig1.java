package com.kaab.crud_app.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "database_1_entityManagerFactory",
        basePackages = "com.kaab.crud_app.repository.userrepo",
        transactionManagerRef = "database_1_transactionManager"
)
public class DatabaseConfig1 {
    @Autowired
    Environment environment;

    @Primary
    @Bean(name = "database_1_dataSource")
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(environment.getProperty("database_1.datasource.url"));
        ds.setUsername(environment.getProperty("database_1.datasource.username"));
        ds.setPassword(environment.getProperty("database_1.datasource.password"));
        ds.setDriverClassName(environment.getProperty("database_1.datasource.driver-class-name"));
        return ds;
    }

    @Primary
    @Bean(name = "database_1_entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManager(){
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(adapter);
        HashMap<String,Object> properties = new HashMap<String,Object>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        bean.setJpaPropertyMap(properties);
        bean.setPackagesToScan("com.kaab.crud_app.entity.user");
        return bean;
    }
    @Primary
    @Bean("database_1_transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("database_1_entityManagerFactory") EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
