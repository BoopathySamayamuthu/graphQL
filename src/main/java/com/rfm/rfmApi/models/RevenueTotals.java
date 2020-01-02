package com.rfm.rfmApi.models;

import lombok.Data;

@Data
public class RevenueTotals {
    private MonthlyTotals baselineTotals;
    private MonthlyTotals mostlikelyTotals;
    private MonthlyTotals upsideTotals;
    private MonthlyTotals fixedCostTotals;

    public RevenueTotals(MonthlyTotals baselineTotals, MonthlyTotals mostlikelyTotals, MonthlyTotals upsideTotals, MonthlyTotals fixedCostTotals) {
        this.baselineTotals = baselineTotals;
        this.mostlikelyTotals = mostlikelyTotals;
        this.upsideTotals = upsideTotals;
        this.fixedCostTotals = fixedCostTotals;
    }
}
