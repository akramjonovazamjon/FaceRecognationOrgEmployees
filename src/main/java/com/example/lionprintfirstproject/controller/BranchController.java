package com.example.lionprintfirstproject.controller;

import com.example.lionprintfirstproject.controller.vm.BranchVm;
import com.example.lionprintfirstproject.dto.ResponseData;
import com.example.lionprintfirstproject.dto.branch.BranchCreate;
import com.example.lionprintfirstproject.dto.branch.BranchUpdate;
import com.example.lionprintfirstproject.entity.Branch;
import com.example.lionprintfirstproject.mapper.BranchMapper;
import com.example.lionprintfirstproject.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody BranchUpdate dto) {
        service.update(id, dto);
    }

    @GetMapping
    public ResponseData<List<BranchVm>> getAll(Pageable pageable) {
        List<Branch> branchList = service.getAll(pageable);
        return ResponseData.of(mapper.asBranchList(branchList));
    }

    @GetMapping("/{id}")
    public ResponseData<BranchVm> getById(@PathVariable Long id) {
        Branch branch = service.getById(id);
        return ResponseData.of(mapper.asBranchVm(branch));
    }

    @GetMapping("/organizations/{organizationId}")
    public ResponseData<List<BranchVm>> getOrganizationBranches(@PathVariable Long organizationId) {
        List<Branch> branches = service.getOrganizationBranches(organizationId);
        return ResponseData.of(mapper.asBranchList(branches));
    }

}
