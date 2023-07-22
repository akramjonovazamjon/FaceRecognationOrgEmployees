package com.example.lionprintfirstproject.controller;

import com.example.lionprintfirstproject.controller.vm.BranchVm;
import com.example.lionprintfirstproject.dto.ResponseData;
import com.example.lionprintfirstproject.dto.branch.BranchCreate;
import com.example.lionprintfirstproject.entity.Branch;
import com.example.lionprintfirstproject.mapper.BranchMapper;
import com.example.lionprintfirstproject.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/branches")
public class BranchController {

    private final BranchService service;
    private final BranchMapper mapper;

    @PostMapping
    public ResponseData<BranchVm> create(@RequestBody BranchCreate dto) {
        Branch branch = service.create(dto);
        return ResponseData.of(mapper.asBranchVm(branch));
    }

}
