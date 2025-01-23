package com.emiro.jobapp.emirojobs.job;

import java.util.List;
import java.util.Optional;

public interface JobService {
    List<Job> findAll();

    void createJob(Job job);

    Optional<Job> findById(Long jobId);

    Optional<Job> updateJob(Long jobId, Job updatedJob);

    boolean deleteJob(Long jobId);
}
