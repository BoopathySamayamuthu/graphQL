package com.rfm.rfmApi.repositories;

import com.rfm.rfmApi.entities.Leaves;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepositoy extends JpaRepository<Leaves, Integer> {
    Leaves findByCtsEmpId(String ctsEmpId);
}
