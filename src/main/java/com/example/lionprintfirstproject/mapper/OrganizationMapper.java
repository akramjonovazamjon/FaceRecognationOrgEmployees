package com.example.lionprintfirstproject.mapper;

import com.example.lionprintfirstproject.controller.vm.OrganizationVm;
import com.example.lionprintfirstproject.dto.organization.OrganizationCreate;
import com.example.lionprintfirstproject.dto.organization.OrganizationUpdate;
import com.example.lionprintfirstproject.entity.Organization;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrganizationMapper {
    OrganizationVm asOrganizationVm(Organization organization);
    Organization asOrganizationByCreate(OrganizationCreate organizationCreate);
    Organization asOrganizationByUpdate(OrganizationUpdate organizationUpdate);
    List<OrganizationVm> asOrganizationVmList(List<Organization> organizations);
}
