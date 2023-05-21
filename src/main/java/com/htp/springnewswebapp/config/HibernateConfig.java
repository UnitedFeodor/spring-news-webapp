package com.htp.springnewswebapp.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class HibernateConfig {
    @SneakyThrows
    @Bean
    public DataSource dataSource(Environment env) {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setDriverClass(env.getProperty("jdbc.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        dataSource.setUser(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));

        dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("c3p0.minPoolSize")));
        dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("c3p0.maxPoolSize")));
        dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("c3p0.maxIdleTime")));
        return dataSource;

    }

    private Properties hibernateProperties(Environment env) {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect",env.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        return properties;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource, Environment env) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.htp.springnewswebapp.entity");
        sessionFactory.setHibernateProperties(hibernateProperties(env));
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
