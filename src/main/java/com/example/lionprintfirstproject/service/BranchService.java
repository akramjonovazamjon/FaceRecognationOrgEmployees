package com.example.lionprintfirstproject.service;

import com.example.lionprintfirstproject.dto.branch.BranchCreate;
import com.example.lionprintfirstproject.dto.branch.BranchUpdate;
import com.example.lionprintfirstproject.entity.Branch;
import com.example.lionprintfirstproject.entity.Organization;
import com.example.lionprintfirstproject.exception.branch.BranchExistException;
import com.example.lionprintfirstproject.exception.branch.BranchNotFoundException;
import com.example.lionprintfirstproject.mapper.BranchMapper;
import com.example.lionprintfirstproject.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository repository;
    private final BranchMapper mapper;
    private final OrganizationService organizationService;

    public Branch create(BranchCreate dto) {

        if (repository.existsByNameAndOrganizationId(dto.name(), dto.organizationId())) {
            throw new BranchExistException();
        }
        Organization organization = organizationService.getById(dto.organizationId());
        Branch branch = mapper.asNewBranch(dto, organization);
        return repository.save(branch);
    }

    public void update(Long id, BranchUpdate dto) {

        if (repository.existsByNameAndOrganizationIdAndIdNot(dto.name(), dto.organizationId(), id)) {
            throw new BranchExistException();
        }
        Branch branch = getById(id);

        Organization organization = organizationService.getById(dto.organizationId());
        branch.update(dto, organization);
        repository.save(branch);
    }

    public Branch getById(Long id) {
        return repository.findById(id).orElseThrow(BranchNotFoundException::new);
    }

    public List<Branch> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public List<Branch> getOrganizationBranches(Long organizationId) {
        return repository.findAllByOrganizationId(organizationId);
    }

}
