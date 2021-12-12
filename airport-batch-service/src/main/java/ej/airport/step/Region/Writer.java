package ej.airport.step.Region;

import ej.airport.dao.RegionDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Log4j2
public class Writer implements ItemWriter, StepExecutionListener {

    private final RegionDao regionDao;

    public Writer(RegionDao regionDao) {
        this.regionDao = regionDao;
    }

    @Override
    public void write(List regions) {
        regionDao.insert(regions);
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