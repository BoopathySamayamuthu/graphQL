package com.rfm.rfmApi.controllers;

import com.rfm.rfmApi.dto.RfmJdbcTemplateImpl;
import com.rfm.rfmApi.models.FixedCostTotals;
import com.rfm.rfmApi.models.Forecast;
import com.rfm.rfmApi.models.RevenueTotals;
import com.rfm.rfmApi.repositories.AllocationRepository;
import com.rfm.rfmApi.services.CalculationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/revenue")
public class RevenueController {

    private static final Logger logger = LogManager.getLogger(RfmJdbcTemplateImpl.class);

    @Autowired
    private AllocationRepository allocationRepository;
    @Autowired
    private CalculationService calculationService;
    @Autowired
    RfmJdbcTemplateImpl rfmJdbcTemplate;

    @GetMapping("/dashboard")
    public RevenueTotals calculations() {
        List<Forecast> forecastList = rfmJdbcTemplate.getForecast();
        List<FixedCostTotals> fixedCostAmounts = rfmJdbcTemplate.getFixedCostAmounts();
        for (FixedCostTotals m : fixedCostAmounts) {
            logger.info(m.getJanCost());
        }
        return calculationService.getTotals(forecastList, fixedCostAmounts);
    }

}
