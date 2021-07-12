package ej.airport.config;

import ej.airport.dto.JobType;
import ej.airport.step.Skipper;
import ej.airport.dao.AirportDao;
import ej.airport.step.Airport.Reader;
import ej.airport.step.Airport.Writer;
import ej.airport.step.Listener;
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
public class AirportDataImportBatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final TaskExecutor taskExecutor;
    private final AirportDao airportDao;

    public AirportDataImportBatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, @Qualifier("jobTaskExecutor") TaskExecutor taskExecutor, AirportDao airportDao) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.taskExecutor = taskExecutor;
        this.airportDao = airportDao;
    }

    @Bean(name = "AIRPORT_DATA_IMPORT")
    public Job job() {
        return jobBuilderFactory
                .get(String.valueOf(JobType.AIRPORT_DATA_IMPORT))
                .incrementer(new RunIdIncrementer())
                .listener(new Listener())
                .flow(airportDataImportStep())
                .end()
                .build();
    }

    @Bean
    public Step airportDataImportStep() {
        return stepBuilderFactory
                .get("airportDataImportStep")
                .chunk(500)
                .reader(new Reader())
                .faultTolerant()
                .skipPolicy(new Skipper())
                .writer(new Writer(airportDao))
                .faultTolerant()
                .skipPolicy(new Skipper())
                .taskExecutor(taskExecutor)
                .throttleLimit(10)
                .build();
    }

}
