package com.rfm.rfmApi.dataFetcher;

import com.rfm.rfmApi.entities.AllocationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rfm.rfmApi.entities.Allocation;
import com.rfm.rfmApi.repositories.AllocationRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * @author n0217055
 *
 */
@Component
public class AllocationFetcher implements DataFetcher<Allocation>{

    @Autowired
    private AllocationRepository allocationRepository;

    @Override
    public Allocation get(DataFetchingEnvironment env) {
        AllocationData allocationData = env.getSource();
        Allocation allocation = null;
        if(allocationData != null) {
            allocation= allocationRepository.findByCtsEmpId(allocationData.getCtsEmpID());
        }
        return allocation;
    }

}
