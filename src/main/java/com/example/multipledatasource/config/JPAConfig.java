package com.example.multipledatasource.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jpa.autoconfigure.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JPAConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource, JpaProperties jpaProperties) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dynamicDataSource);
        em.setPackagesToScan("com.example.multipledatasource.model");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Map<String, Object> properties = new HashMap<>(jpaProperties.getProperties());
        properties.put("hibernate.hbm2ddl.auto", jpaProperties.getProperties().getOrDefault("hibernate.hbm2ddl.auto", "update"));
        properties.put("hibernate.show_sql", jpaProperties.isShowSql());
        em.setJpaPropertyMap(properties);
        em.getJpaPropertyMap().put(
                "hibernate.dialect",
                "org.hibernate.dialect.MySQLDialect"
        );
        return em;
    }
    @Bean
    public JpaTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
