package edu.kata.task231.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "edu.kata.task231")
public class HibernateConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("hibernate.connection.driver_class")));
        dataSource.setUsername(environment.getProperty("database.username"));
        dataSource.setPassword(environment.getProperty("database.password"));
        dataSource.setUrl(environment.getProperty("database.url"));
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        localContainerEntityManagerFactoryBean.setPackagesToScan(new String[]{"edu.kata.task231"});
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setJpaProperties(hibernateProperties());
        return localContainerEntityManagerFactoryBean;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        properties.put("hibernate.connection.driver_class", environment.getProperty("hibernate.connection.driver_class"));
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        return properties;
    }
}
