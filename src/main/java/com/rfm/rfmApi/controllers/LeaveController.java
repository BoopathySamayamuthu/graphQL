package com.rfm.rfmApi.controllers;

import com.rfm.rfmApi.dto.RfmJdbcTemplateImpl;
import com.rfm.rfmApi.entities.Leaves;
import com.rfm.rfmApi.models.LeaveModel;
import com.rfm.rfmApi.repositories.LeaveRepositoy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LeaveController {
    private static final Logger logger = LogManager.getLogger(ProjectController.class);
    @Autowired
    private LeaveRepositoy leaveRepositoy;
    @Autowired
    RfmJdbcTemplateImpl rfmJdbcTemplate;

    @GetMapping("/leaves")
    public List<LeaveModel> index() {
        logger.info("Request received to add a New Allocation ");
        return rfmJdbcTemplate.getLeaves();
    }

    @PostMapping("/leaves")
    public Leaves create(@Valid @RequestBody Leaves leaves) {
        logger.info("Request received to add a New Leave request for {} from {} to {} ", leaves.getCtsEmpId(), leaves.getLeaveStartDate(), leaves.getLeaveEndDate());
        return leaveRepositoy.save(leaves);
    }

}
