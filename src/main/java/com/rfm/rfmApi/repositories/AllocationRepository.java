package com.rfm.rfmApi.repositories;

import com.rfm.rfmApi.entities.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Integer> {
    List<Allocation> findByRfmProjectId(int rfmProjectID);
    Optional<Allocation> findByCtsEmpIdAndAllocationActiveStatus(String ctsEmpId, boolean activeStatus);
    Allocation findByCtsEmpId(String ctsEmpId);


   /* @Query(value = " SELECT  r.client_emp_id, r.cts_emp_id, r.first_name, r.last_name, r.cts_department, r.location, " +
            "p.esa_project_id, p.esa_project_name, p. sow_project_id, p.sow_project_name, p.fixed_bid_flag, p.tnm_flag,  p.project_active_status, p.department, p.sbu, " +
            "a.bill_rate, a.hours, a.allocation_type, a.allocation_active_status,a.financial_year, a.jan_allocation, a.feb_allocation, a.mar_allocation, a.apr_allocation, a.may_allocation, a.jun_allocation," +
            " a.jul_allocation, a.aug_allocation, a.sep_allocation, a.oct_allocation, a.nov_allocation, a.dec_allocation " +
            "FROM" +
            " allocation a, " +
            "project p," +
            " resource r " +
            "WHERE a.ESA_PROJECT_ID = p.ESA_PROJECT_ID " +
            "AND r.RESOURCE_ID = a.RESOURCE_ID"
            , nativeQuery = true)
    List<Object[]> getForeCastData();*/


}



