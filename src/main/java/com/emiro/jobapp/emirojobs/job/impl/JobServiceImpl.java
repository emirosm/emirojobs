package com.emiro.jobapp.emirojobs.job.impl;

import com.emiro.jobapp.emirojobs.job.Job;
import com.emiro.jobapp.emirojobs.job.JobRepository;
import com.emiro.jobapp.emirojobs.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    private JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Optional<Job> findById(Long jobId) {
        return jobRepository.findById(jobId);
    }

    @Override
    public Optional<Job> updateJob(Long jobId, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if (jobOptional.isPresent()) {
            updatedJob.setId(jobId);
            jobRepository.save(updatedJob);
            return jobOptional;
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteJob(Long jobId) {
        jobRepository.deleteById(jobId);
        return !findById(jobId).isPresent();
    }


}
