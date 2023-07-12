package com.example.lionprintfirstproject.service;

import com.example.lionprintfirstproject.dto.job.CreateJob;
import com.example.lionprintfirstproject.entity.Job;
import com.example.lionprintfirstproject.exception.job.JobExistException;
import com.example.lionprintfirstproject.exception.job.JobNotFoundException;
import com.example.lionprintfirstproject.mapper.JobMapper;
import com.example.lionprintfirstproject.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JobService {
    private final JobRepository repository;
    private final JobMapper mapper;

    public Job create(CreateJob dto) {

        if (repository.existsByName(dto.name())) {
            throw new JobExistException();
        }

        Job job = mapper.asNewJob(dto);

        return repository.save(job);
    }

    public List<Job> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public Job getById(Long id) {
        return repository.findById(id).orElseThrow(JobNotFoundException::new);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
