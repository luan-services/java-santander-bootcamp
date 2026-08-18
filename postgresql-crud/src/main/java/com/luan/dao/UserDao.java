package com.luan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.luan.config.ConnectionFactory;
import com.luan.exception.DatabaseException;
import com.luan.model.User;

public class UserDao {
    private static final String INSERT_USER = """
            INSERT INTO users (name, email)
            VALUES (?, ?)
            RETURNING id
            """;

    public User create(User user) {
        /* question marks keep values separate from the SQL command */
        try (Connection connection = ConnectionFactory.openConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {

            /* parameter indexes start at one and follow the SQL question marks */
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());

            /* PostgreSQL RETURNING produces a ResultSet containing the generated id */
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new DatabaseException("The database did not return the generated user id");
                }

                /* the generated database id is assigned to the same User object */
                user.setId(resultSet.getLong("id"));
                return user;
            }
        } catch (SQLException exception) {
            throw new DatabaseException("Could not create the user", exception);
        }
    }
}
