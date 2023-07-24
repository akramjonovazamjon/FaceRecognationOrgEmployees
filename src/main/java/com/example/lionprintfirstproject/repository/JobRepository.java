package com.example.lionprintfirstproject.repository;

import com.example.lionprintfirstproject.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    boolean existsByName(String name);
    Optional<Job> findByName(String name);

}
