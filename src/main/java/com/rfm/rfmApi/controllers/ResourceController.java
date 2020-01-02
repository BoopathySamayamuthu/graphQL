package com.rfm.rfmApi.controllers;

import com.rfm.rfmApi.entities.Resource;
import com.rfm.rfmApi.repositories.ResourceRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourceController {
    private static final Logger logger = LogManager.getLogger(ResourceController.class);

    @Autowired
    private ResourceRepository resourceRepository;


    //Get All resources
    @GetMapping("/getResources")
    public List<Resource> index() {

        logger.info("Requesting details of all resources ");
        return resourceRepository.findAll();
    }

    //Get Resource by resource active status and location
    @GetMapping("/findBy")
    public List<Resource> findByActiveAndLocation(@RequestParam(value = "isActive") Boolean isActive, @RequestParam(value = "location") String loc) {
        logger.info("Requesting details of resources with active status {}  and are located in {} ", isActive, loc);

        return resourceRepository.findByActiveStatusAndLocation(isActive, loc);
    }

    //Create a New Resource
    @PostMapping("/addResource")
    public Resource create(@Valid @RequestBody Resource resource) {
        logger.info("Request received to add a Resource with first name {} and Last name {} ", resource.getFirstName(), resource.getLastName());
        return resourceRepository.save(resource);

    }

    //Update a resource by cts emp id
    @PutMapping("/update/{id}")
    public Resource update(@PathVariable(value = "id") String id, @RequestBody Resource resource) {

        Resource r = (resourceRepository.findByCtsEmpId(id).orElseThrow(() -> new IllegalArgumentException("Cts Emp Id Not found")));

        r.setCtsEmpId(resource.getCtsEmpId());
        r.setClientEmpId(resource.getClientEmpId());
        r.setFirstName(resource.getFirstName());
        r.setLastName(resource.getLastName());
        r.setCtsDesignation(resource.getCtsDesignation());
        r.setCtsDepartment(resource.getCtsDepartment());
        r.setClientRole(resource.getClientRole());
        r.setPrimarySkill(resource.getPrimarySkill());
        r.setLocation(resource.getLocation());
        r.setOffice(resource.getOffice());
        r.setActiveStatus(resource.getActiveStatus());

        return r;

    }

    @PostMapping("/bulkUpload")
    public List<Resource> createBatch(@Valid @RequestBody List<Resource> resourceList) {
        logger.info("Request received to add a batch of resources containing {} entries ", resourceList.size());

        batchInsert(resourceList, resourceRepository);

        return resourceList;

    }

    public static void batchInsert(@RequestBody @Valid List<Resource> resourceList, ResourceRepository resourceRepository) {
        int size = resourceList.size();
        int counter = 0;
        List<Resource> temp = new ArrayList<>();
        for (Resource resource : resourceList) {
            temp.add(resource);
            resourceRepository.save(temp.get(counter));
            temp.clear();

        }
    }

}
