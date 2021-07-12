package ej.airport.step.Country;

import ej.airport.dao.CountryDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Log4j2
public class Writer implements ItemWriter, StepExecutionListener {

    private final CountryDao countryDao;

    public Writer(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public void write(List countries) {
        countryDao.insert(countries);
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("Line inserted: {} ", stepExecution.getWriteCount());
        return null;
    }

}