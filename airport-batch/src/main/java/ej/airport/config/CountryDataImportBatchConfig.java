package ej.airport.config;

import ej.airport.dto.JobType;
import ej.airport.step.Skipper;
import ej.airport.dao.CountryDao;
import ej.airport.step.Country.Reader;
import ej.airport.step.Country.Writer;
import ej.airport.step.Listener;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

@Configuration
@RequiredArgsConstructor
public class CountryDataImportBatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final TaskExecutor taskExecutor;
    private final CountryDao countryDao;

    @Bean(name = "COUNTRY_DATA_IMPORT")
    public Job job() {
        return jobBuilderFactory
                .get(String.valueOf(JobType.COUNTRY_DATA_IMPORT))
                .incrementer(new RunIdIncrementer())
                .listener(new Listener())
                .flow(countryDataImportStep())
                .end()
                .build();
    }

    @Bean
    public Step countryDataImportStep() {
        return stepBuilderFactory
                .get("countryDataImportStep")
                .chunk(500)
                .reader(new Reader())
                .faultTolerant()
                .skipPolicy(new Skipper())
                .writer(new Writer(countryDao))
                .faultTolerant()
                .skipPolicy(new Skipper())
                .taskExecutor(taskExecutor)
                .throttleLimit(10)
                .build();
    }

}
