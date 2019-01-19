package com.chortitzer.cin.cinfxboot.datasource.bascula;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "basculaEntityManagerFactory",
        transactionManagerRef = "basculaTransactionManager",
        basePackages = { "com.chortitzer.cin.cinfxboot.datasource.bascula.repository" }
)
public class BasculaDbConfig {

    @Bean(name = "basculaDataSource")
    @ConfigurationProperties(prefix = "bascula.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "basculaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    barEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("basculaDataSource") DataSource dataSource
    ) {
        return
                builder
                        .dataSource(dataSource)
                        .packages("com.chortitzer.cin.cinfxboot.datasource.bascula.domain")
                        .persistenceUnit("bascula")
                        .build();
    }
    @Bean(name = "basculaTransactionManager")
    public PlatformTransactionManager barTransactionManager(
            @Qualifier("basculaEntityManagerFactory") EntityManagerFactory
                    barEntityManagerFactory
    ) {
        return new JpaTransactionManager(barEntityManagerFactory);
    }
}
