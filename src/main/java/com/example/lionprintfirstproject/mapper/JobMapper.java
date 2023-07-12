package com.example.lionprintfirstproject.mapper;

import com.example.lionprintfirstproject.controller.vm.JobVm;
import com.example.lionprintfirstproject.dto.job.CreateJob;
import com.example.lionprintfirstproject.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface JobMapper {

    @Mapping(target = "id", ignore = true)
    Job asNewJob(CreateJob dto);

    JobVm asJobVm(Job job);

    List<JobVm> asJobList(List<Job> jobs);

}
