package com.example.lionprintfirstproject.controller.vm;

public record BranchVm(
        Long id,
        String name,
        String info,
        OrganizationVm organization
) {
}
