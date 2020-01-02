package com.rfm.rfmApi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveModel {

    private int ctsEmpId;
    private String firstName;
    private String lastName;
    private Timestamp appliedDate;
    private Timestamp leaveStartDate;
    private Timestamp leaveEndDate;
    private double leaveDays;
    private String leaveType;
    private String leaveStatus;
    private boolean isApproved;
    private String reason;
    private int financialYear;
    private double janLeaves;
    private double febLeaves;
    private double marLeaves;
    private double aprLeaves;
    private double mayLeaves;
    private double junLeaves;
    private double julLeaves;
    private double augLeaves;
    private double sepLeaves;
    private double octLeaves;
    private double novLeaves;
    private double decLeaves;
}
