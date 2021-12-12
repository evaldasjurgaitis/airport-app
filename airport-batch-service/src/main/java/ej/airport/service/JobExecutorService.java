package ej.airport.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class JobExecutorService {

    private final ApplicationContext applicationContext;
    private final JobLauncher jobLauncher;

    public void executeJob(String jobType, String filePath) {
        try {
            jobLauncher.run(applicationContext.getBean(jobType, Job.class), new JobParametersBuilder()
                    .addString("job-id", UUID.randomUUID().toString())
                    .addString("filePath", filePath)
                    .toJobParameters());
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

}
