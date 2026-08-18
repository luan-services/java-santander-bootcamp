package com.luan.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {
    private ConnectionFactory() {
    }

    public static Connection openConnection() throws SQLException {
        /* DriverManager uses the PostgreSQL JDBC driver declared in pom.xml */
        return DriverManager.getConnection(
                DatabaseConfig.getUrl(),
                DatabaseConfig.getUser(),
                DatabaseConfig.getPassword());
    }
}
