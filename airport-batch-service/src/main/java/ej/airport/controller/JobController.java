package ej.airport.controller;

import ej.airport.dto.Job;
import ej.airport.dto.JobExecute;
import ej.airport.dto.JobType;
import ej.airport.service.JobExecutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobExecutorService jobExecutorService;
    private final JobExplorer jobExplorer;
    
    @GetMapping("/{jobType}")
    public Job getJob(@PathVariable("jobType") JobType jobType) {
        JobInstance jobInstance = jobExplorer.getLastJobInstance(String.valueOf(jobType));
        return new Job(jobInstance.getInstanceId(), BatchStatus.UNKNOWN);
    }

    @GetMapping("/{id}/status")
    public Job getJobWithStatus(@PathVariable("id") Long id) {
        return new Job(id, jobExplorer.getJobExecution(id).getStatus());
    }

    @Async
    @PostMapping("/{jobType}/execute")
    public void executeJob(@PathVariable("jobType") JobType jobType, @RequestBody JobExecute jobExecute) {
        jobExecutorService.executeJob(String.valueOf(jobType), jobExecute.getFilePath());
    }

}
