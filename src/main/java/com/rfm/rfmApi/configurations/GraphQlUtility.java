package com.rfm.rfmApi.configurations;

import static graphql.GraphQL.newGraphQL;
import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.rfm.rfmApi.dataFetcher.AllocationDataFetcher;
import com.rfm.rfmApi.dataFetcher.AllocationFetcher;
import com.rfm.rfmApi.dataFetcher.LeaveDataFetcher;
import com.rfm.rfmApi.dataFetcher.ProjectDataFetcher;
import com.rfm.rfmApi.dataFetcher.ResourceDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Component
public class GraphQlUtility {
    @Value("classpath:schemas.graphqls")
    private Resource schemaResource;
    private GraphQL graphQL;
    private AllocationDataFetcher allocationDataFetcher;
    private ResourceDataFetcher resourceDataFetcher;
    private ProjectDataFetcher projectDataFetcher;
    private AllocationFetcher allocationFetcher;
    private LeaveDataFetcher leaveDataFetcher;

    GraphQlUtility(AllocationDataFetcher allocationDataFetcher, ResourceDataFetcher resourceDataFetcher,ProjectDataFetcher projectDataFetcher, AllocationFetcher allocationFetcher, LeaveDataFetcher leaveDataFetcher  ){
        this.allocationDataFetcher = allocationDataFetcher;
        this.resourceDataFetcher = resourceDataFetcher;
        this.projectDataFetcher = projectDataFetcher;
        this.allocationFetcher = allocationFetcher;
        this.leaveDataFetcher = leaveDataFetcher;

    }

    @PostConstruct
    public GraphQL createGraphQlObject() throws IOException {
        File schemas = schemaResource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemas);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        return newGraphQL(schema).build();
    }

    public RuntimeWiring buildRuntimeWiring(){
        return newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allocationData", allocationDataFetcher))
                .type("AllocationData", typeWiring -> typeWiring
                        .dataFetcher("resource", resourceDataFetcher)
                        .dataFetcher("allocation", allocationFetcher))
                .type("Allocation", typeWiring -> typeWiring
                        .dataFetcher("project", projectDataFetcher))
                .type("Resource", typeWiring -> typeWiring
                        .dataFetcher("leaveData", leaveDataFetcher))
                .build();
    }
}
