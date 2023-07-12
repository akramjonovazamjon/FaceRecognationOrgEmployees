package com.example.lionprintfirstproject.controller;

import com.example.lionprintfirstproject.controller.vm.JobVm;
import com.example.lionprintfirstproject.dto.ResponseData;
import com.example.lionprintfirstproject.dto.job.CreateJob;
import com.example.lionprintfirstproject.entity.Job;
import com.example.lionprintfirstproject.mapper.JobMapper;
import com.example.lionprintfirstproject.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobController {
    private final JobService service;
    private final JobMapper mapper;

    @PostMapping
    public ResponseData<JobVm> create(@RequestBody @Valid CreateJob dto) {
        Job job = service.create(dto);
        return ResponseData.of(mapper.asJobVm(job));
    }

    @GetMapping
    public ResponseData<List<JobVm>> getAll(Pageable pageable) {
        List<Job> jobList = service.getAll(pageable);
        return ResponseData.of(mapper.asJobList(jobList));
    }

    @GetMapping("/{id}")
    public ResponseData<JobVm> getById(@PathVariable Long id) {
        Job job = service.getById(id);
        return ResponseData.of(mapper.asJobVm(job));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
