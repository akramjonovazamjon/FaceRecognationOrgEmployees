package com.example.lionprintfirstproject.repository;

import com.example.lionprintfirstproject.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    boolean existsByName(String name);
}
