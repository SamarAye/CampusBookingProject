package com.crbas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/campus_booking?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1991";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // force driver load
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL driver not found. Put mysql-connector-j jar in WEB-INF/lib or Tomcat/lib.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}