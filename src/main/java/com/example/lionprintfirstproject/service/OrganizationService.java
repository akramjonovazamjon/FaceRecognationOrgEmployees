package com.example.lionprintfirstproject.service;

import com.example.lionprintfirstproject.dto.organization.OrganizationCreate;
import com.example.lionprintfirstproject.entity.Organization;
import com.example.lionprintfirstproject.exception.organization.OrganizationExistByNameException;
import com.example.lionprintfirstproject.exception.organization.OrganizationNotFoundException;
import com.example.lionprintfirstproject.mapper.OrganizationMapper;
import com.example.lionprintfirstproject.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    public Organization create(OrganizationCreate organizationCreate) {
        if (organizationRepository.existsByName(organizationCreate.name())) {
            throw new OrganizationExistByNameException(organizationCreate.name());
        }
        Organization organization = organizationMapper.asOrganization(organizationCreate);
        return organizationRepository.save(organization);
    }

    public Organization getById(Long id) {
        return organizationRepository.findById(id).orElseThrow(OrganizationNotFoundException::new);
    }
}
