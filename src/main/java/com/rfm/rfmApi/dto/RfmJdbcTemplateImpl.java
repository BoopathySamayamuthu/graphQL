package com.rfm.rfmApi.dto;

import com.rfm.rfmApi.configurations.DataSourceConfiguration;
import com.rfm.rfmApi.models.FixedCostTotals;
import com.rfm.rfmApi.models.Forecast;
import com.rfm.rfmApi.models.LeaveModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class RfmJdbcTemplateImpl {

    private static final Logger logger = LogManager.getLogger(RfmJdbcTemplateImpl.class);

    private DataSource dataSource = DataSourceConfiguration.getDataSource();
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    public List<Forecast> getForecast() {

        String forecastSQL = "SELECT  r.client_emp_id, r.cts_emp_id, r.first_name, r.last_name, r.cts_department, r.location, p.esa_project_id, p.esa_project_name, p. sow_project_id, p.sow_project_name, p.project_type,  p.project_active_status, p.department, a.bill_rate, a.hours, a.allocation_type, a.allocation_active_status, a.financial_year, a.jan_allocation, a.feb_allocation, a.mar_allocation, a.apr_allocation, a.may_allocation, a.jun_allocation, a.jul_allocation, a.aug_allocation, a.sep_allocation, a.oct_allocation, a.nov_allocation, a.dec_allocation " +
                "FROM allocation a, project p, resource r " +
                "WHERE a.rfm_project_id = p.rfm_project_id " +
                "AND a.cts_emp_id = r.cts_emp_id";

        List<Forecast> forecasts = jdbcTemplate.query(forecastSQL, BeanPropertyRowMapper.newInstance(Forecast.class));
        logger.info("The Number of forecast rows are {}", forecasts.size());

        return forecasts;

    }

    public List<LeaveModel> getLeaves() {

        String leavesSQL = "SELECT l.cts_emp_id, r.first_name, r.last_name, l.applied_date, l.leave_start_date, l.leave_end_date, l.leave_days, l.leave_type, l.leave_status, l.is_approved, l.reason, l.financial_year, l.jan_leaves, l.feb_leaves, l.mar_leaves, l.apr_leaves, l.may_leaves, l.jun_leaves, l.jul_leaves, l.aug_leaves, l.sep_leaves, l.oct_leaves, l.nov_leaves, l.dec_leaves " +
                "FROM  leaves l , resource r " +
                "WHERE l.cts_emp_id = r.cts_emp_id";

        List<LeaveModel> leaves = jdbcTemplate.query(leavesSQL, BeanPropertyRowMapper.newInstance(LeaveModel.class));
        logger.info("The Number of leave rows are {}", leaves.size());

        return leaves;
    }

    public List<FixedCostTotals> getFixedCostAmounts() {

        String fixedCostSQL = "SELECT f.rfm_project_id, f.jan_cost, f.feb_cost, f.mar_cost, f.apr_cost, f.may_cost, f.jun_cost, f.jul_cost, f.aug_cost, f.sep_cost, f.oct_cost, f.nov_cost, f.dec_cost FROM fixedcost f";


        List<FixedCostTotals> fixedCost = jdbcTemplate.query(fixedCostSQL, BeanPropertyRowMapper.newInstance(FixedCostTotals.class));
        logger.info("The Number of fixed project rows are {}", fixedCost.size());

        return fixedCost;
    }

}
