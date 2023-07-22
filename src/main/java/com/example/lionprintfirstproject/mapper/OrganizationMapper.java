package com.example.lionprintfirstproject.mapper;

import com.example.lionprintfirstproject.controller.vm.OrganizationVm;
import com.example.lionprintfirstproject.dto.organization.OrganizationCreate;
import com.example.lionprintfirstproject.entity.Organization;
import org.mapstruct.Mapper;

@Mapper
public interface OrganizationMapper {
    OrganizationVm asOrganizationVm(Organization organization);
    Organization asOrganization(OrganizationCreate organizationCreate);
}
