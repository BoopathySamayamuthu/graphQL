package com.rfm.rfmApi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FixedCostTotals {

    private String rfmProjectId;
    private double janCost;
    private double febCost;
    private double marCost;
    private double aprCost;
    private double mayCost;
    private double junCost;
    private double julCost;
    private double augCost;
    private double sepCost;
    private double octCost;
    private double novCost;
    private double decCost;
}
