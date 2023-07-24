package com.example.lionprintfirstproject.repository;

import com.example.lionprintfirstproject.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    boolean existsByName(String name);
    Optional<Organization> findByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
}
