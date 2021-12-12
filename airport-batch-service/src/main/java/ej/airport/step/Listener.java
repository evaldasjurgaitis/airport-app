package ej.airport.step;

import ej.airport.util.FileUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

@Log4j2
public class Listener extends JobExecutionListenerSupport {

    @Override
    public void afterJob(JobExecution jobExecution) {
        JobParameters jobParameters = jobExecution.getJobParameters();
        String filePath = jobParameters.getString("filePath");
        log.info("File deleted(true/false) = [{}] / [{}].", FileUtil.deleteFile(filePath), filePath);
        log.info("Job finished!");
    }

}