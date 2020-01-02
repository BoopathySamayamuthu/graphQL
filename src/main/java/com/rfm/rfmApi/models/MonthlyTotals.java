package com.rfm.rfmApi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyTotals {

    private double januaryTotal;
    private double februaryTotal;
    private double marchTotal;
    private double aprilTotal;
    private double mayTotal;
    private double juneTotal;
    private double julyTotal;
    private double augustTotal;
    private double septemberTotal;
    private double octoberTotal;
    private double novemberTotal;
    private double decemberTotal;
    private double grandTotal;
}
