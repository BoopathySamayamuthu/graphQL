package com.rfm.rfmApi.services;

import com.rfm.rfmApi.enums.AllocationType;
import com.rfm.rfmApi.enums.CalendarMonths;
import com.rfm.rfmApi.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculationServiceImpl implements CalculationService {
    private static final Logger logger = LogManager.getLogger(CalculationServiceImpl.class);

    private MonthlyTotals blTotals;
    private MonthlyTotals mlTotals;
    private MonthlyTotals upsideTotals;
    private MonthlyTotals fixedCostTotals;
    
    @Override
    public RevenueTotals getTotals(List<Forecast> forecastList, List<FixedCostTotals> fixedCostTotalsList) {
        blTotals = new MonthlyTotals();
        mlTotals = new MonthlyTotals();
        upsideTotals = new MonthlyTotals();
        fixedCostTotals = new MonthlyTotals();


        for (Forecast forecast : forecastList) {
            if (forecast.getProjectType().equalsIgnoreCase("TM")) {
                if (forecast.getAllocationActiveStatus() && !"BISQUAD".equalsIgnoreCase(forecast.getCtsDepartment())) {
                    if (forecast.getAllocationType().equalsIgnoreCase(String.valueOf(AllocationType.BASELINE))) {
                        getRevenueByAllocation(blTotals, forecast);

                    } else if (forecast.getAllocationType().equalsIgnoreCase(String.valueOf(AllocationType.MOSTLIKELY))) {
                        getRevenueByAllocation(mlTotals, forecast);

                    } else if (forecast.getAllocationType().equalsIgnoreCase(String.valueOf(AllocationType.UPSIDE))) {
                        getRevenueByAllocation(upsideTotals, forecast);

                    }
                }
            }                        

        }

        fixedCostTotals = getFixedAmounts(fixedCostTotalsList);
        logger.info(" FC total for January " + fixedCostTotals.getJanuaryTotal());
        logger.info(" FC total for February " + fixedCostTotals.getFebruaryTotal());
        logger.info(" FC total for March " + fixedCostTotals.getMarchTotal());
        logger.info(" FC total for April " + fixedCostTotals.getAprilTotal());
        logger.info(" FC total for May " + fixedCostTotals.getMayTotal());
        logger.info(" FC total for June " + fixedCostTotals.getJuneTotal());
        logger.info(" FC total for July " + fixedCostTotals.getJulyTotal());
        logger.info(" FC total for August " + fixedCostTotals.getAugustTotal());
        logger.info(" FC total for September " + fixedCostTotals.getSeptemberTotal());
        logger.info(" FC total for October " + fixedCostTotals.getOctoberTotal());
        logger.info(" FC total for November " + fixedCostTotals.getNovemberTotal());
        logger.info(" FC total for December " + fixedCostTotals.getDecemberTotal());

        return new RevenueTotals(blTotals, mlTotals, upsideTotals, fixedCostTotals);
    }

    /**
     * @param fixedCostTotalsList
     * @return Totalled fixedcost Object
     */
    public MonthlyTotals getFixedAmounts(List<FixedCostTotals> fixedCostTotalsList) {
        fixedCostTotals = new MonthlyTotals();
        for (FixedCostTotals fc : fixedCostTotalsList) {
            getRevenueForFixedcostProject(fixedCostTotals, fc);
        }

        fixedCostTotals.setGrandTotal(fixedCostTotals.getJanuaryTotal() + fixedCostTotals.getFebruaryTotal() + fixedCostTotals.getMarchTotal() +
                fixedCostTotals.getAprilTotal() + fixedCostTotals.getMayTotal() + fixedCostTotals.getJuneTotal() + fixedCostTotals.getJulyTotal() + fixedCostTotals.getAugustTotal() +
                fixedCostTotals.getSeptemberTotal() + fixedCostTotals.getOctoberTotal() + fixedCostTotals.getNovemberTotal() + fixedCostTotals.getDecemberTotal());

        return fixedCostTotals;
    }

    /**
     * @param monthlyTotals Object containing totals by month
     * @param forecast      List from sql query returning forecast objects
     * @return Object containing totals by month and grand total
     */
    private MonthlyTotals getRevenueByAllocation(MonthlyTotals monthlyTotals, Forecast forecast) {
        double monthTotal;


        for (CalendarMonths cal : CalendarMonths.values()) {
            if (cal == CalendarMonths.JANUARY) {
                if (forecast.getLocation().equalsIgnoreCase("offshore")) {
                    monthTotal = forecast.getJanAllocation() * forecast.getBillRate() * forecast.getHours() * OffshoreWorkingDays.JANUARY;
                } else {
                    monthTotal = forecast.getJanAllocation() * forecast.getBillRate() * forecast.getHours() * OnsiteWorkingDays.JANUARY;
                }
                monthlyTotals.setJanuaryTotal(monthlyTotals.getJanuaryTotal() + monthTotal);
            } else if (cal == CalendarMonths.FEBRUARY) {
                if (forecast.getLocation().equalsIgnoreCase("offshore")) {
                    monthTotal = forecast.getFebAllocation() * forecast.getBillRate() * forecast.getHours() * OffshoreWorkingDays.FEBRUARY;
                } else {
                    monthTotal = forecast.getFebAllocation() * forecast.getBillRate() * forecast.getHours() * OnsiteWorkingDays.FEBRUARY;
                }
                monthlyTotals.setFebruaryTotal(monthlyTotals.getFebruaryTotal() + monthTotal);
            } else if (cal == CalendarMonths.MARCH) {
                if (forecast.getLocation().equalsIgnoreCase("offshore")) {
                    monthTotal = forecast.getMarAllocation() * forecast.getBillRate() * forecast.getHours() * OffshoreWorkingDays.MARCH;
                } else {
                    monthTotal = forecast.getMarAllocation() * forecast.getBillRate() * forecast.getHours() * OnsiteWorkingDays.MARCH;
                }
                monthlyTotals.setMarchTotal(monthlyTotals.getMarchTotal() + monthTotal);
            } else if (cal == CalendarMonths.APRIL) {
                if (forecast.getLocation().equalsIgnoreCase("offshore")) {
                    monthTotal = forecast.getAprAllocation() * forecast.getBillRate() * forecast.getHours() * OffshoreWorkingDays.APRIL;
                } else {
                    monthTotal = forecast.getAprAllocation() * forecast.getBillRate() * forecast.getHours() * OnsiteWorkingDays.APRIL;
                }
                monthlyTotals.setAprilTotal(monthlyTotals.getAprilTotal() + monthTotal);
            } else if (cal == CalendarMonths.MAY) {
                if (forecast.getLocation().equalsIgnoreCase("offshore")) {
                    monthTotal = forecast.getMayAllocation() * forecast.getBillRate() * forecast.getHours() * OffshoreWorkingDays.MAY;
                } else {
                    monthTotal = forecast.getMayAllocation() * forecast.getBillRate() * forecast.getHours() * OnsiteWorkingDays.MAY;
                }

                monthlyTotals.setMayTotal(monthlyTotals.getMayTotal() + monthTotal);
            } else if (cal == CalendarMonths.JUNE) {
                if (forecast.getLocation().equalsIgnoreCase("offshore")) {
                    monthTotal = forecast.getJunAllocation() * forecast.getBillRate() * forecast.getHours() * OffshoreWorkingDays.JUNE;
                } else {
                    monthTotal = forecast.getJunAllocation() * forecast.getBillRate() * forecast.getHours() * OnsiteWorkingDays.JUN;
                }

                monthlyTotals.setJuneTotal(monthlyTotals.getJuneTotal() + monthTotal);
            } else if (cal == CalendarMonths.JULY) {
                if (forecast.getLocation().equalsIgnoreCase("offshore")) {
                    monthTotal = forecast.getJulAllocation() * forecast.getBillRate() * forecast.getHours() * OffshoreWorkingDays.JULY;
                } else {
                    monthTotal = forecast.getJulAllocation() * forecast.getBillRate() * forecast.getHours() * OnsiteWorkingDays.JULY;
                }

                monthlyTotals.setJulyTotal(monthlyTotals.getJulyTotal() + monthTotal);
            } else if (cal == CalendarMonths.AUGUST) {
                if (forecast.getLocation().equalsIgnoreCase("offshore")) {
                    monthTotal = forecast.getAugAllocation() * forecast.getBillRate() * forecast.getHours() * OffshoreWorkingDays.AUGUST;
                } else {
                    monthTotal = forecast.getAugAllocation() * forecast.getBillRate() * forecast.getHours() * OnsiteWorkingDays.AUGUST;
                }
                monthlyTotals.setAugustTotal(monthlyTotals.getAugustTotal() + monthTotal);
            } else if (cal == CalendarMonths.SEPTEMBER) {
                if (forecast.getLocation().equalsIgnoreCase("offshore")) {
                    monthTotal = forecast.getSepAllocation() * forecast.getBillRate() * forecast.getHours() * OffshoreWorkingDays.SEPTEMBER;
                } else {
                    monthTotal = forecast.getSepAllocation() * forecast.getBillRate() * forecast.getHours() * OnsiteWorkingDays.SEPTEMBER;
                }

                monthlyTotals.setSeptemberTotal(monthlyTotals.getSeptemberTotal() + monthTotal);
            } else if (cal == CalendarMonths.OCTOBER) {
                if (forecast.getLocation().equalsIgnoreCase("offshore")) {
                    monthTotal = forecast.getOctAllocation() * forecast.getBillRate() * forecast.getHours() * OffshoreWorkingDays.OCTOBER;
                } else {
                    monthTotal = forecast.getOctAllocation() * forecast.getBillRate() * forecast.getHours() * OnsiteWorkingDays.OCTOBER;
                }
                monthlyTotals.setOctoberTotal(monthlyTotals.getOctoberTotal() + monthTotal);
            } else if (cal == CalendarMonths.NOVEMBER) {
                if (forecast.getLocation().equalsIgnoreCase("offshore")) {
                    monthTotal = forecast.getNovAllocation() * forecast.getBillRate() * forecast.getHours() * OffshoreWorkingDays.NOVEMBER;
                } else {
                    monthTotal = forecast.getNovAllocation() * forecast.getBillRate() * forecast.getHours() * OnsiteWorkingDays.NOVEMBER;
                }
                monthlyTotals.setNovemberTotal(monthlyTotals.getNovemberTotal() + monthTotal);
            } else if (cal == CalendarMonths.DECEMBER) {
                if (forecast.getLocation().equalsIgnoreCase("offshore")) {
                    monthTotal = forecast.getDecAllocation() * forecast.getBillRate() * forecast.getHours() * OffshoreWorkingDays.DECEMBER;
                } else {
                    monthTotal = forecast.getDecAllocation() * forecast.getBillRate() * forecast.getHours() * OnsiteWorkingDays.DECEMBER;

                }
                monthlyTotals.setDecemberTotal(monthlyTotals.getDecemberTotal() + monthTotal);
            }

        }
        monthlyTotals.setGrandTotal(monthlyTotals.getJanuaryTotal() + monthlyTotals.getFebruaryTotal() + monthlyTotals.getMarchTotal() +
                monthlyTotals.getAprilTotal() + monthlyTotals.getMayTotal() + monthlyTotals.getJuneTotal() + monthlyTotals.getJulyTotal() + monthlyTotals.getAugustTotal() +
                monthlyTotals.getSeptemberTotal() + monthlyTotals.getOctoberTotal() + monthlyTotals.getNovemberTotal() + monthlyTotals.getDecemberTotal());

        return monthlyTotals;
    }

    private MonthlyTotals getRevenueForFixedcostProject(MonthlyTotals fixedCostMonthlyTotal, FixedCostTotals fixedCostTotals) {
        double monthTotal;
        for (CalendarMonths cal : CalendarMonths.values()) {
            if (cal == CalendarMonths.JANUARY) {
                monthTotal = fixedCostTotals.getJanCost();
                fixedCostMonthlyTotal.setJanuaryTotal(fixedCostMonthlyTotal.getJanuaryTotal() + monthTotal);
            } else if (cal == CalendarMonths.FEBRUARY) {
                monthTotal = fixedCostTotals.getFebCost();
                fixedCostMonthlyTotal.setFebruaryTotal(fixedCostMonthlyTotal.getFebruaryTotal() + monthTotal);
            } else if (cal == CalendarMonths.MARCH) {
                monthTotal = fixedCostTotals.getMarCost();
                fixedCostMonthlyTotal.setMarchTotal(fixedCostMonthlyTotal.getMarchTotal() + monthTotal);
            } else if (cal == CalendarMonths.APRIL) {
                monthTotal = fixedCostTotals.getAprCost();
                fixedCostMonthlyTotal.setAprilTotal(fixedCostMonthlyTotal.getAprilTotal() + monthTotal);
            } else if (cal == CalendarMonths.MAY) {
                monthTotal = fixedCostTotals.getMayCost();
                fixedCostMonthlyTotal.setMayTotal(fixedCostMonthlyTotal.getMayTotal() + monthTotal);
            } else if (cal == CalendarMonths.JUNE) {
                monthTotal = fixedCostTotals.getJunCost();
                fixedCostMonthlyTotal.setJuneTotal(fixedCostMonthlyTotal.getJuneTotal() + monthTotal);
            } else if (cal == CalendarMonths.JULY) {
                monthTotal = fixedCostTotals.getJulCost();
                fixedCostMonthlyTotal.setJulyTotal(fixedCostMonthlyTotal.getJulyTotal() + monthTotal);
            } else if (cal == CalendarMonths.AUGUST) {
                monthTotal = fixedCostTotals.getAugCost();
                fixedCostMonthlyTotal.setAugustTotal(fixedCostMonthlyTotal.getAugustTotal() + monthTotal);
            } else if (cal == CalendarMonths.SEPTEMBER) {
                monthTotal = fixedCostTotals.getSepCost();
                fixedCostMonthlyTotal.setSeptemberTotal(fixedCostMonthlyTotal.getSeptemberTotal() + monthTotal);
            } else if (cal == CalendarMonths.OCTOBER) {
                monthTotal = fixedCostTotals.getOctCost();
                fixedCostMonthlyTotal.setOctoberTotal(fixedCostMonthlyTotal.getOctoberTotal() + monthTotal);
            } else if (cal == CalendarMonths.NOVEMBER) {
                monthTotal = fixedCostTotals.getNovCost();
                fixedCostMonthlyTotal.setNovemberTotal(fixedCostMonthlyTotal.getNovemberTotal() + monthTotal);
            } else if (cal == CalendarMonths.DECEMBER) {
                monthTotal = fixedCostTotals.getDecCost();
                fixedCostMonthlyTotal.setDecemberTotal(fixedCostMonthlyTotal.getDecemberTotal() + monthTotal);
            }
        }

        return fixedCostMonthlyTotal;
    }
    
}
