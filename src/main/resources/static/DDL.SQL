drop table project;
CREATE TABLE project
(
    rfm_project_id        VARCHAR(50) NOT NULL PRIMARY KEY,
    esa_project_id        VARCHAR(30) NOT NULL,
    esa_project_name      VARCHAR(255) DEFAULT NULL,
    account_name          VARCHAR(255) DEFAULT NULL,
    sow_project_id        VARCHAR(30) NOT NULL,
    sow_project_name      VARCHAR(255) DEFAULT NULL,
    project_active_status BIT(1)       DEFAULT NULL,
    department            VARCHAR(255) DEFAULT NULL,
    project_type          VARCHAR(255) DEFAULT NULL,
    sbu                   VARCHAR(255) DEFAULT NULL
) ENGINE = InnoDB;

drop table resource;
CREATE TABLE resource
(
    cts_emp_id      VARCHAR(30)  NOT NULL PRIMARY KEY,
    client_emp_id   varchar(255) NOT NULL,
    first_name      varchar(255) DEFAULT NULL,
    last_name       varchar(255) DEFAULT NULL,
    cts_designation varchar(255) DEFAULT NULL,
    cts_department  varchar(255) DEFAULT NULL,
    client_role     varchar(255) DEFAULT NULL,
    primary_skill   varchar(255) DEFAULT NULL,
    location        varchar(255) DEFAULT NULL,
    office          varchar(255) DEFAULT NULL,
    active_status   bit(1)       DEFAULT NULL

) ENGINE = InnoDB;

drop table allocation;
CREATE TABLE allocation
(
    allocation_id            INT AUTO_INCREMENT PRIMARY KEY,
    allocation_active_status bit(1)       DEFAULT NULL,
    bill_rate                double       DEFAULT NULL,
    cts_emp_id               VARCHAR(30) NOT NULL,
    rfm_project_id           VARCHAR(50) NOT NULL,
    financial_year           int(11)      DEFAULT NULL,
    hours                    int(11)      DEFAULT NULL,
    allocation_type          varchar(255) DEFAULT "BASELINE",
    jan_allocation           double       DEFAULT NULL,
    feb_allocation           double       DEFAULT NULL,
    mar_allocation           double       DEFAULT NULL,
    apr_allocation           double       DEFAULT NULL,
    may_allocation           double       DEFAULT NULL,
    jun_allocation           double       DEFAULT NULL,
    jul_allocation           double       DEFAULT NULL,
    aug_allocation           double       DEFAULT NULL,
    sep_allocation           double       DEFAULT NULL,
    oct_allocation           double       DEFAULT NULL,
    nov_allocation           double       DEFAULT NULL,
    dec_allocation           double       DEFAULT NULL,

    FOREIGN KEY (rfm_project_id) REFERENCES revenueforecastmanager.project (rfm_project_id),
    FOREIGN KEY (cts_emp_id) REFERENCES revenueforecastmanager.resource (cts_emp_id)

) ENGINE = InnoDB;

drop table leaves;
CREATE TABLE leaves
(
    leave_id         int(11) AUTO_INCREMENT PRIMARY KEY,
    cts_emp_id       VARCHAR(30) NOT NULL,
    applied_date     DATETIME     DEFAULT NULL,
    leave_start_date DATETIME     DEFAULT NULL,
    leave_end_date   DATETIME     DEFAULT NULL,
    leave_days       double       DEFAULT NULL,
    leave_type       VARCHAR(255) DEFAULT NULL,
    leave_status     VARCHAR(255) DEFAULT NULL,
    reason           VARCHAR(255) DEFAULT NULL,
    financial_year   int(11)      DEFAULT NULL,
    is_approved      bit(1)       DEFAULT NULL,
    jan_leaves       double       DEFAULT NULL,
    feb_leaves       double       DEFAULT NULL,
    mar_leaves       double       DEFAULT NULL,
    apr_leaves       double       DEFAULT NULL,
    may_leaves       double       DEFAULT NULL,
    jun_leaves       double       DEFAULT NULL,
    jul_leaves       double       DEFAULT NULL,
    aug_leaves       double       DEFAULT NULL,
    sep_leaves       double       DEFAULT NULL,
    oct_leaves       double       DEFAULT NULL,
    nov_leaves       double       DEFAULT NULL,
    dec_leaves       double       DEFAULT NULL,
    FOREIGN KEY (cts_emp_id) REFERENCES revenueforecastmanager.resource (cts_emp_id)

) ENGINE = InnoDB;

drop table fixedcost;
CREATE TABLE fixedcost
(
    id             int(11) AUTO_INCREMENT PRIMARY KEY,
    rfm_project_id VARCHAR(50) DEFAULT NULL,
    financial_year int(11)     DEFAULT NULL,
    jan_cost       double      DEFAULT NULL,
    feb_cost       double      DEFAULT NULL,
    mar_cost       double      DEFAULT NULL,
    apr_cost       double      DEFAULT NULL,
    may_cost       double      DEFAULT NULL,
    jun_cost       double      DEFAULT NULL,
    jul_cost       double      DEFAULT NULL,
    aug_cost       double      DEFAULT NULL,
    sep_cost       double      DEFAULT NULL,
    oct_cost       double      DEFAULT NULL,
    nov_cost       double      DEFAULT NULL,
    dec_cost       double      DEFAULT NULL,
    FOREIGN KEY (rfm_project_id) REFERENCES revenueforecastmanager.project (rfm_project_id)
) ENGINE = InnoDB;

/*##INSERT TO PROJECT

INSERT INTO PROJECT (esa_project_id, esa_project_name, account_name, sow_project_id,
                     sow_project_name, active_status, department, fixed_bid_flag, sbu, tnm_flag)
VALUES (1000239272, "LM GRM Products QE Transform", "Liberty Mutual Insurance", 414,
        "GRM Products & Underwriting - Strategic Dev & QE Support",
        TRUE, "U&P", FALSE, "GRM", TRUE);

SELECT *
FROM PROJECT;

## INSERT TO RESOURCE
INSERT INTO RESOURCE
(client_emp_id, cts_department, cts_emp_id, first_name, last_name,
 location, onsite_office, active_status)
VALUES ("N0264517", "QE&A", 427985, "THANGAVEL", "RAMANATHAN", "ONSITE", "DOVER", TRUE);

SELECT *
FROM RESOURCE;

## INSERT ROW TO ALLOCATION

INSERT INTO ALLOCATION (active_status, bill_rate, resource_id, esa_project_id, financial_year,
                        hours, allocation_type, jan_allocation, feb_allocation,
                        mar_allocation, apr_allocation, may_allocation, jun_allocation,
                        jul_allocation, aug_allocation, sep_allocation, oct_allocation,
                        nov_allocation, dec_allocation)
VALUES (TRUE, 80.00, 1, 1000239272, 2020, 8, "BASELINE",
        1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
        1.0, 1.0, 1.0, 1.0, 1.0, 1.0);

SELECT *
FROM ALLOCATION;
*/
