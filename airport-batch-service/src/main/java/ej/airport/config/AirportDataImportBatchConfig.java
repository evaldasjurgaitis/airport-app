package ej.airport.config;

import ej.airport.dto.JobType;
import ej.airport.model.Airport;
import ej.airport.model.ModifyAirport;
import ej.airport.service.PriceService;
import ej.airport.step.Airport.Processor;
import ej.airport.step.Skipper;
import ej.airport.dao.AirportDao;
import ej.airport.step.Airport.Reader;
import ej.airport.step.Airport.Writer;
import ej.airport.step.Listener;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.integration.async.AsyncItemProcessor;
import org.springframework.batch.integration.async.AsyncItemWriter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.task.TaskExecutor;

@Configuration
@RequiredArgsConstructor
public class AirportDataImportBatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final TaskExecutor taskExecutor;
    private final AirportDao airportDao;
    private final PriceService priceService;

    @Bean(name = "AIRPORT_DATA_IMPORT")
    public Job job() throws Exception {
        return jobBuilderFactory
                .get(String.valueOf(JobType.AIRPORT_DATA_IMPORT))
                .incrementer(new RunIdIncrementer())
                .listener(new Listener())
                .flow(airportDataImportStep())
                .end()
                .build();
    }

    @Bean
    public Step airportDataImportStep() throws Exception {
        return stepBuilderFactory
                .get("airportDataImportStep")
                .chunk(50)
                .reader(new Reader())
                .faultTolerant()
                .skipPolicy(new Skipper())
                .processor((ItemProcessor) asyncItemProcessor())
                .writer(asyncItemWriter())
                .faultTolerant()
                .skipPolicy(new Skipper())
                .taskExecutor(taskExecutor)
                .throttleLimit(20)
                .build();
    }

    @Bean
    @StepScope
    public AsyncItemWriter<Airport> asyncItemWriter() throws Exception {
        AsyncItemWriter<Airport> asyncItemWriter = new AsyncItemWriter<>();
        asyncItemWriter.setDelegate(new Writer(airportDao));
        asyncItemWriter.afterPropertiesSet();
        return asyncItemWriter;
    }

    @Bean
    @StepScope
    public AsyncItemProcessor<Airport, ModifyAirport> asyncItemProcessor() throws Exception {
        AsyncItemProcessor<Airport, ModifyAirport> asyncItemProcessor = new AsyncItemProcessor<>();
        asyncItemProcessor.setDelegate(new Processor(priceService));
        asyncItemProcessor.afterPropertiesSet();
        asyncItemProcessor.setTaskExecutor(taskExecutor);
        return asyncItemProcessor;
    }

}
