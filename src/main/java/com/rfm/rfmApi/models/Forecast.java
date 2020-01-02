package com.rfm.rfmApi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Forecast {

    private String clientEmpId;
    private String ctsEmpId;
    private String firstName;
    private String lastName;
    private String ctsDepartment;
    private String location;

    private String esaProjectId;
    private String esaProjectName;
    private String sowProjectId;
    private String sowProjectName;
    private String projectType;
    private Boolean projectActiveStatus;
    private String department;
    private String sbu;


    private double billRate;
    private int hours;
    private String allocationType;
    private Boolean allocationActiveStatus;
    private int financialYear;
    private double janAllocation;
    private double febAllocation;
    private double marAllocation;
    private double aprAllocation;
    private double mayAllocation;
    private double junAllocation;
    private double julAllocation;
    private double augAllocation;
    private double sepAllocation;
    private double octAllocation;
    private double novAllocation;
    private double decAllocation;

    public Forecast(String clientEmpId, String ctsEmpId, String firstName, String lastName, String ctsDepartment, String location, String esaProjectId, String esaProjectName, String sowProjectId, String sowProjectName, String projectType, Boolean projectActiveStatus, String department, String sbu, double billRate, int hours, String allocationType, Boolean allocationActiveStatus, int financialYear, double janAllocation, double febAllocation, double marAllocation, double aprAllocation, double mayAllocation, double junAllocation, double julAllocation, double augAllocation, double sepAllocation, double octAllocation, double novAllocation, double decAllocation) {
        this.clientEmpId = clientEmpId;
        this.ctsEmpId = ctsEmpId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ctsDepartment = ctsDepartment;
        this.location = location;
        this.esaProjectId = esaProjectId;
        this.esaProjectName = esaProjectName;
        this.sowProjectId = sowProjectId;
        this.sowProjectName = sowProjectName;
        this.projectType = projectType;
        this.projectActiveStatus = projectActiveStatus;
        this.department = department;
        this.sbu = sbu;
        this.billRate = billRate;
        this.hours = hours;
        this.allocationType = allocationType;
        this.allocationActiveStatus = allocationActiveStatus;
        this.financialYear = financialYear;
        this.janAllocation = janAllocation;
        this.febAllocation = febAllocation;
        this.marAllocation = marAllocation;
        this.aprAllocation = aprAllocation;
        this.mayAllocation = mayAllocation;
        this.junAllocation = junAllocation;
        this.julAllocation = julAllocation;
        this.augAllocation = augAllocation;
        this.sepAllocation = sepAllocation;
        this.octAllocation = octAllocation;
        this.novAllocation = novAllocation;
        this.decAllocation = decAllocation;
    }
}
