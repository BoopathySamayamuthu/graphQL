package com.rfm.rfmApi.dataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rfm.rfmApi.entities.AllocationData;
import com.rfm.rfmApi.entities.Resource;
import com.rfm.rfmApi.repositories.ResourceRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * @author n0217055
 *
 */
@Component
public class ResourceDataFetcher implements DataFetcher<Resource>{

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public Resource get(DataFetchingEnvironment env) {
        AllocationData allocationData = env.getSource();
        Resource resource = null;
        if(allocationData != null) {
            resource = (resourceRepository.findByCtsEmpId(allocationData.getCtsEmpID())).orElse(null);
        }
        return resource;
    }
}

