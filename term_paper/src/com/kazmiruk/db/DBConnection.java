package com.kazmiruk.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The class with which the program connects to the database
 */
public class DBConnection {
    private static final String URL = "jdbc:sqlserver://DESKTOP-MVRGHFV\\MSSQLSERVER:1433;database=touristTickets";
    private static final String USER = "Dima";
    private static final String PASSWORD = "12345678";
    private static Connection connection = null;
    public static Connection connection() {
        if (connection != null)
            return connection;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
