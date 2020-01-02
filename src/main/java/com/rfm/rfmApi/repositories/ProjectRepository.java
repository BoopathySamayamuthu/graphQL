package com.rfm.rfmApi.repositories;

import com.rfm.rfmApi.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Optional<Project> findBySowProjectId(String sowProjectId);
}
