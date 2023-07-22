package com.example.lionprintfirstproject.controller;

import com.example.lionprintfirstproject.controller.vm.OrganizationVm;
import com.example.lionprintfirstproject.dto.ResponseData;
import com.example.lionprintfirstproject.dto.organization.OrganizationCreate;
import com.example.lionprintfirstproject.dto.organization.OrganizationUpdate;
import com.example.lionprintfirstproject.entity.Organization;
import com.example.lionprintfirstproject.mapper.OrganizationMapper;
import com.example.lionprintfirstproject.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.apache.http.protocol.RequestDate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/organizations")
public class OrganizationController {
    private final OrganizationService organizationService;
    private final OrganizationMapper organizationMapper;
    @PostMapping("")
    public ResponseData<OrganizationVm> create(@RequestBody OrganizationCreate organizationCreate){
        Organization organization = organizationService.create(organizationCreate);
        OrganizationVm organizationVm = organizationMapper.asOrganizationVm(organization);
        return ResponseData.of(organizationVm);
    }
    @GetMapping("/{id}")
    public ResponseData<OrganizationVm> getById(@PathVariable Long id){
        Organization organization = organizationService.getById(id);
        OrganizationVm organizationVm = organizationMapper.asOrganizationVm(organization);
        return ResponseData.of(organizationVm);
    }
    @GetMapping("")
    public ResponseData<List<OrganizationVm>> getAll(){
        List<Organization> organizations = organizationService.getAll();
        List<OrganizationVm> organizationVmList = organizationMapper.asOrganizationVmList(organizations);
        return ResponseData.of(organizationVmList);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        organizationService.delete(id);
    }
    @PutMapping("/{id}")
    public void updateById(@RequestBody OrganizationUpdate organizationUpdate, @PathVariable Long id){
        organizationService.update(organizationUpdate, id);
    }
}
