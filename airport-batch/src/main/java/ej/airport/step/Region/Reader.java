package ej.airport.step.Region;

import ej.airport.model.Region;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

@Log4j2
@StepScope
public class Reader extends FlatFileItemReader<Region> implements StepExecutionListener {

    public Reader() {
        super();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[]{"id", "code", "name", "continent", "iso_country"});

        BeanWrapperFieldSetMapper<Region> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Region.class);


        DefaultLineMapper<Region> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        setLinesToSkip(1);
        setLineMapper(lineMapper);
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {

        JobParameters jobParameters = stepExecution.getJobParameters();
        String filePath = jobParameters.getString("filePath");
        log.info("filePath = [{}].", filePath);

        FileSystemResource resource = new FileSystemResource(filePath);
        setResource(resource);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("Line read: {} ", stepExecution.getReadCount());
        return null;
    }

}
