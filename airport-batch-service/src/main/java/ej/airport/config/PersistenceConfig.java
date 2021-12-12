package ej.airport.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfig {

    @Primary
    @Bean(name = "airportBatchDS")
    @ConfigurationProperties(prefix = "spring.airport-batch.datasource")
    public DataSource airportBatchDS() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "airportDS")
    @ConfigurationProperties(prefix = "spring.airport.datasource")
    public DataSource airportDS() {
        return DataSourceBuilder.create().build();
    }

}

