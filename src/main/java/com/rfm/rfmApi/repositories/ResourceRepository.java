package com.rfm.rfmApi.repositories;

import com.rfm.rfmApi.entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
    List<Resource> findByActiveStatusAndLocation(Boolean isActive, String location);

    Optional<Resource> findByCtsEmpId(String ctsEmpId);
}
