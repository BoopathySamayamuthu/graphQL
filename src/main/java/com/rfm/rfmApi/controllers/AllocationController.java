package com.rfm.rfmApi.controllers;

import com.rfm.rfmApi.dto.RfmJdbcTemplateImpl;
import com.rfm.rfmApi.entities.Allocation;
import com.rfm.rfmApi.exceptions.ResourceNotFoundException;
import com.rfm.rfmApi.models.Forecast;
import com.rfm.rfmApi.repositories.AllocationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/allocations")
public class AllocationController {
    private static final Logger logger = LogManager.getLogger(AllocationController.class);

    @Autowired
    private AllocationRepository allocationRepository;
    @Autowired
    RfmJdbcTemplateImpl rfmJdbcTemplate;

    //Get All Allocations
    @GetMapping("/getAllocations")
    public Page<Allocation> index(Pageable pageable) {
        logger.info("Requesting list of all allocations ");
        return allocationRepository.findAll(pageable);
    }

    //Get Allocation by sow project id
    @GetMapping("/findBy")
    public List<Allocation> show(@RequestParam(value = "rfmProjectId") int id) {
        logger.info("Requesting allocation details with id: " + id);

        return allocationRepository.findByRfmProjectId(id);
    }

    //Create a new Entry
    @PostMapping("/addAllocation")
    public Allocation create(@Valid @RequestBody Allocation allocation) {
        logger.info("Request received to add a New Allocation ");
        return allocationRepository.save(allocation);

    }

    //Get All Forecast Data
    @GetMapping("/forecast")
    public List<Forecast> retrieve() {
        logger.info("Requesting forecast details for all resources ");
        return rfmJdbcTemplate.getForecast();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Allocation> update(@PathVariable(value = "id") String id, @RequestBody Allocation allocation) throws ResourceNotFoundException {

        Allocation a;
        a = (allocationRepository.findByCtsEmpIdAndAllocationActiveStatus(id, true).orElseThrow(() -> new ResourceNotFoundException("Allocation not found for cts employee id :" + id)));

        a.setAllocationActiveStatus(allocation.getAllocationActiveStatus());
        a.setBillRate(allocation.getBillRate());
        a.setFinancialYear(allocation.getFinancialYear());
        a.setHours(allocation.getHours());
        a.setAllocationType(allocation.getAllocationType());
        a.setJanAllocation(allocation.getJanAllocation());
        a.setFebAllocation(allocation.getFebAllocation());
        a.setMarAllocation(allocation.getMarAllocation());
        a.setAprAllocation(allocation.getAprAllocation());
        a.setMayAllocation(allocation.getMayAllocation());
        a.setJunAllocation(allocation.getJunAllocation());
        a.setJulAllocation(allocation.getJulAllocation());
        a.setAugAllocation(allocation.getAugAllocation());
        a.setSepAllocation(allocation.getSepAllocation());
        a.setOctAllocation(allocation.getOctAllocation());
        a.setOctAllocation(allocation.getOctAllocation());
        a.setNovAllocation(allocation.getNovAllocation());
        a.setDecAllocation(allocation.getDecAllocation());

        final Allocation updatedAllocation = allocationRepository.save(a);

        return ResponseEntity.ok(updatedAllocation);

    }

    @PostMapping("/bulkUpload")
    public List<Allocation> createBatch(@Valid @RequestBody List<Allocation> allocationList) {
        logger.info("Request received to add a batch of resources containing {} entries ", allocationList.size());

        batchInsert(allocationList, allocationRepository);

        return allocationList;

    }

    public static void batchInsert(@RequestBody @Valid List<Allocation> allocationList, AllocationRepository allocationRepository) {
        int size = allocationList.size();
        int counter = 0;
        List<Allocation> temp = new ArrayList<>();
        for (Allocation allocation : allocationList) {
            temp.add(allocation);
            allocationRepository.save(temp.get(counter));
            temp.clear();

        }
    }

}
