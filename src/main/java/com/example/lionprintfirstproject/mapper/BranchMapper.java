package com.example.lionprintfirstproject.mapper;

import com.example.lionprintfirstproject.controller.vm.BranchVm;
import com.example.lionprintfirstproject.dto.branch.BranchCreate;
import com.example.lionprintfirstproject.entity.Branch;
import com.example.lionprintfirstproject.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {OrganizationMapper.class})
public interface BranchMapper {

    @Mapping(target = "id", ignore = true)
    Branch asNewBranch(BranchCreate dto, Organization organization);

    BranchVm asBranchVm(Branch branch);

    List<BranchVm> asBranchList(List<Branch> branchList);

}
