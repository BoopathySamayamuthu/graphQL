package com.rfm.rfmApi.services;

import com.rfm.rfmApi.models.FixedCostTotals;
import com.rfm.rfmApi.models.Forecast;
import com.rfm.rfmApi.models.RevenueTotals;

import java.util.List;

public interface CalculationService {

    RevenueTotals getTotals(List<Forecast> forecastList, List<FixedCostTotals> fixedCostTotalsList);
}
