package com.example.lionprintfirstproject.repository;

import com.example.lionprintfirstproject.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    boolean existsByNameAndOrganizationId(String name, Long organizationId);

    Optional<Branch> findByNameAndOrganizationId(String name, Long organizationId);

    boolean existsByNameAndOrganizationIdAndIdNot(String name, Long organizationId, Long id);

    List<Branch> findAllByOrganizationId(Long organizationId);
}
