package ej.airport.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "ej.airport.repository"},
        transactionManagerRef = "airportTransactionManager",
        entityManagerFactoryRef = "airportEntityManagerFactory"
)
@EntityScan(basePackages = "ej.airport.entity")
@ComponentScan(basePackages = { "ej.airport" })
public class PersistenceConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean(name= "airportDataSource")
    public DataSource airportDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "airportEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean airportEntityManagerFactory(
            final EntityManagerFactoryBuilder builder,
            final DataSource airportDataSource) {
        return builder
                .dataSource(airportDataSource)
                .packages(
                        "ej.airport.entity"
                )
                .persistenceUnit("entityPersistenceUnit")
                .build();
    }

    @Bean(name = "airportTransactionManager")
    public PlatformTransactionManager airportTransactionManager(final EntityManagerFactory airportEntityManagerFactory) {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                airportEntityManagerFactory);
        return transactionManager;
    }

}

