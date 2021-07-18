package ej.airport.config;

import ej.airport.dto.JobType;
import ej.airport.step.Skipper;
import ej.airport.dao.RegionDao;
import ej.airport.step.Listener;
import ej.airport.step.Region.Reader;
import ej.airport.step.Region.Writer;
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
public class RegionDataImportConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final RegionDao regionDao;
    private final TaskExecutor taskExecutor;

    @Bean(name = "REGION_DATA_IMPORT")
    public Job job() {
        return jobBuilderFactory
                .get(String.valueOf(JobType.REGION_DATA_IMPORT))
                .incrementer(new RunIdIncrementer())
                .listener(new Listener())
                .flow(regionDataImportStep())
                .end()
                .build();
    }

    @Bean
    public Step regionDataImportStep() {
        return stepBuilderFactory
                .get("regionDataImportStep")
                .chunk(500)
                .reader(new Reader())
                .faultTolerant()
                .skipPolicy(new Skipper())
                .writer(new Writer(regionDao))
                .faultTolerant()
                .skipPolicy(new Skipper())
                .taskExecutor(taskExecutor)
                .throttleLimit(10)
                .build();
    }

}
