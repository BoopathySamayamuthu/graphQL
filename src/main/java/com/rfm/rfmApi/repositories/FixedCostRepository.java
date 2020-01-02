package com.rfm.rfmApi.repositories;

import com.rfm.rfmApi.entities.FixedCost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixedCostRepository extends JpaRepository<FixedCost, Integer> {
}
