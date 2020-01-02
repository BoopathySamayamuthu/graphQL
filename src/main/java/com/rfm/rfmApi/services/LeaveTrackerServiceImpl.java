package com.rfm.rfmApi.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;

public class LeaveTrackerServiceImpl implements LeaveTrackerService {
    private static final Logger logger = LogManager.getLogger(LeaveTrackerServiceImpl.class);
    static Calendar cal = Calendar.getInstance();
    static int year = 2020;
    static int month = Calendar.JANUARY;

    public static void main(String[] argv) throws Exception {

        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        logger.info("There are {} days in the current Month", days);

        logger.info("There value is  {} for  Month", month);

    }

    public void DaysinMonth() {

    }


}
