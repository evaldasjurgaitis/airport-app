package ej.airport.step.Airport;

import ej.airport.dao.AirportDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Log4j2
public class Writer implements ItemWriter, StepExecutionListener {

    private final AirportDao airportDao;

    public Writer(AirportDao airportDao) {
        this.airportDao = airportDao;
    }

    @Override
    public void write(List airports) {
        airportDao.insert(airports);
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