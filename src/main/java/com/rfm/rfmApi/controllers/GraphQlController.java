package com.rfm.rfmApi.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.rfm.rfmApi.configurations.GraphQlUtility;


import graphql.ExecutionResult;
import graphql.GraphQL;

@RestController
public class GraphQlController {
    private GraphQL graphQL;
    private GraphQlUtility graphQlUtility;

    @Autowired
    GraphQlController(GraphQlUtility graphQlUtility) throws IOException {
        this.graphQlUtility = graphQlUtility;
        graphQL = graphQlUtility.createGraphQlObject();
    }

    @PostMapping(value = "/query")
    public ResponseEntity query(@RequestBody String query){
        ExecutionResult result = graphQL.execute(query);
        System.out.println("errors: "+result.getErrors() + query);
        return ResponseEntity.ok(result.getData());
    }
}
