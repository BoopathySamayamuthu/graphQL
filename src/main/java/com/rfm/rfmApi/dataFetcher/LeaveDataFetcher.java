package com.rfm.rfmApi.dataFetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.rfm.rfmApi.entities.Leaves;
import com.rfm.rfmApi.entities.Resource;
import com.rfm.rfmApi.repositories.LeaveRepositoy;


import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * @author n0217055
 *
 */
@Component
public class LeaveDataFetcher implements DataFetcher<Leaves>{
    @Autowired
    private LeaveRepositoy leaveRepository;

    @Override
    public Leaves get(DataFetchingEnvironment env) {
        Resource resource = env.getSource();
        Leaves leaves = null;
        if(resource != null) {
            leaveRepository.findByCtsEmpId(resource.getCtsEmpId());
        }
        return leaves;
    }


}
