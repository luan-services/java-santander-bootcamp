package com.luan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.luan.config.ConnectionFactory;
import com.luan.exception.DatabaseException;
import com.luan.model.UserSummary;

public class UserSummaryDao {
    private static final String FIND_ALL_SUMMARIES = """
            SELECT id, name, email, display_name
            FROM user_summary
            ORDER BY id
            """;

    public List<UserSummary> findAll() {
        List<UserSummary> summaries = new ArrayList<>();

        /* the view is queried like a table even though its data comes from users */
        try (Connection connection = ConnectionFactory.openConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_ALL_SUMMARIES);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                summaries.add(new UserSummary(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("display_name")));
            }

            return summaries;
        } catch (SQLException exception) {
            throw new DatabaseException("Could not query the user summary view", exception);
        }
    }
}
