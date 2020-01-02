package com.rfm.rfmApi.configurations;


import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataSourceConfiguration {

    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static String url;
    private static String dbUsername;
    private static String dbPassword;

    private static DataSource dataSource;

    public DataSourceConfiguration() throws FileNotFoundException {

    }

    public static DriverManagerDataSource getDataSource() {
        LoadProperties();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);

        return dataSource;
    }

    public static void LoadProperties() {
        try (InputStream input = DataSourceConfiguration.class.getClassLoader().getResourceAsStream("application-local.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            url = prop.getProperty("spring.datasource.url");
            dbUsername = prop.getProperty("spring.datasource.username");
            dbPassword = prop.getProperty("spring.datasource.password");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
