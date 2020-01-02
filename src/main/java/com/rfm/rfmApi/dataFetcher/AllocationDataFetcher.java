package com.rfm.rfmApi.dataFetcher;

import com.rfm.rfmApi.entities.AllocationData;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.Map;
import org.springframework.stereotype.Component;


/**
 * @author n0217055
 *
 */
@Component
public class AllocationDataFetcher implements DataFetcher<AllocationData> {

    @Override
    public AllocationData get(DataFetchingEnvironment env) {
        Map args = env.getArguments();
        String ctsEmpID = String.valueOf(args.get("ctsEmpID"));
        AllocationData allocationData = new AllocationData();
        allocationData.setCtsEmpID(ctsEmpID);
        return allocationData;
    }

}