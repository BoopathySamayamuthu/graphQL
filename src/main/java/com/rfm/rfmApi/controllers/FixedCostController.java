package com.rfm.rfmApi.controllers;

import com.rfm.rfmApi.entities.FixedCost;
import com.rfm.rfmApi.repositories.FixedCostRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fixedCosts")
public class FixedCostController {
    private static final Logger logger = LogManager.getLogger(FixedCostController.class);

    @Autowired
    private FixedCostRepository fixedCostRepository;

    @ApiOperation(value = "View a list of available projects", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
            @ApiResponse(code = 403, message = "Forbidden", response = String.class),
            @ApiResponse(code = 404, message = "The vendors were not found, ensure the query parameters are correct.", response = String.class),
            @ApiResponse(code = 405, message = "Validation exception for the API invocation.", response = String.class)})
    @GetMapping("/getFixedCosts")
    public ResponseEntity<List<FixedCost>> index() {
        logger.info("Requesting details of all projects ");
        List<FixedCost> fixedCostProjects = fixedCostRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(fixedCostProjects);
    }

    //Create a new Entry
    @PostMapping("/addFixedCost")
    public FixedCost create(@Valid @RequestBody FixedCost fixedCost) {
        logger.info("Request received to add a Fixedcost project ");
        return fixedCostRepository.save(fixedCost);

    }

    @PostMapping("/bulkUpload")
    public List<FixedCost> createBatch(@Valid @RequestBody List<FixedCost> fixedCostList) {
        logger.info("Request received to add a batch of Fixedcost projects containing {} entries ", fixedCostList.size());

        batchInsert(fixedCostList, fixedCostRepository);

        return fixedCostList;

    }

    public static void batchInsert(@RequestBody @Valid List<FixedCost> fixedCostList, FixedCostRepository fixedCostRepository) {
        int size = fixedCostList.size();
        int counter = 0;
        List<FixedCost> temp = new ArrayList<>();
        for (FixedCost fixedCost : fixedCostList) {
            temp.add(fixedCost);
            fixedCostRepository.save(temp.get(counter));
            temp.clear();

        }
    }
}
