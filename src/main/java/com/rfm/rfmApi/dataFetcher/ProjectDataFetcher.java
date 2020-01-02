package com.rfm.rfmApi.dataFetcher;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rfm.rfmApi.entities.Allocation;
import com.rfm.rfmApi.entities.Project;
import com.rfm.rfmApi.repositories.ProjectRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * @author n0217055
 *
 */
@Component
public class ProjectDataFetcher implements DataFetcher<Project>{

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project get(DataFetchingEnvironment env) {
        Allocation allocation = env.getSource();
        Project project = null;
        if(allocation != null) {
            projectRepository.findBySowProjectId(allocation.getRfmProjectId());
        }
        return project;
    }
}

