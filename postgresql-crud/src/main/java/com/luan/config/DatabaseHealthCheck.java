package com.luan.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class DatabaseHealthCheck {
    private static final String CHECK_QUERY = "SELECT 1 AS result";

    private DatabaseHealthCheck() {
    }

    public static boolean isDatabaseAvailable() throws SQLException {
        /* try-with-resources closes JDBC resources in reverse creation order */
        try (Connection connection = ConnectionFactory.openConnection();
                PreparedStatement statement = connection.prepareStatement(CHECK_QUERY);
                ResultSet resultSet = statement.executeQuery()) {

            /* next moves the cursor to the first row returned by the query */
            return resultSet.next() && resultSet.getInt("result") == 1;
        }
    }
}
