package com.java.config;

import java.io.IOException;
import java.util.Properties;

import com.java.dto.*;
import com.java.util.LoggerSingleton;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.java")
@EnableWebMvc
public class SpringConfig {
    @Value("${url}")// Spring will inject the value when creating the object for configuration
    String url;
    @Value("${password}")
    String password;
    @Value("${username}")
    String username;
    @Value("${driverClassName}")
    String driverClassName;
    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(url);
        ds.setPassword(password);
        ds.setUsername(username);
        ds.setDriverClassName(driverClassName);
        ds.setMaxTotal(100);
        ds.setMaxIdle(20);
        return ds;
    }

    //Configures db with database.properties file. Make it static so that this method is loaded before dataSource
    @Bean
    public static PropertyPlaceholderConfigurer getProperty() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocation(new ClassPathResource("database.properties"));
        return ppc;
    }
    @Bean("sessionFactory")
    public SessionFactory sessionFactory() throws IOException {
        LoggerSingleton.getLogger().info("In the sessionFactory method");
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(Environment.SHOW_SQL, "true");
        hibernateProperties.setProperty(Environment.DIALECT, "org.hibernate.dialect.Oracle12cDialect");
        hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, "update");
        factoryBean.setAnnotatedClasses(Friend.class, PostLike.class, Post.class, Comment.class, CommentLike.class);
        factoryBean.setHibernateProperties(hibernateProperties);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean
    public HibernateTransactionManager transactionManager() throws IOException {
        HibernateTransactionManager tx= new HibernateTransactionManager();
        tx.setSessionFactory(sessionFactory());
        return tx;
    }
}
