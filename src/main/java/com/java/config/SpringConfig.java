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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.MultipartConfigElement;

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
        //hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, "update");
        factoryBean.setAnnotatedClasses(Friend.class, PostLike.class, Post.class, Comment.class, CommentLike.class);
        hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, "update");
        factoryBean.setAnnotatedClasses(Friend.class, PostLike.class, Post.class, Comment.class, CommentLike.class, Uuidclass.class);
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

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000000);
        return multipartResolver;
    }
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
         
        mailSender.setUsername("jortestguy@gmail.com");
        mailSender.setPassword("drawposs");
         
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

         
        return mailSender;
    }
}
