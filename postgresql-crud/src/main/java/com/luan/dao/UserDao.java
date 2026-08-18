package com.luan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.luan.config.ConnectionFactory;
import com.luan.exception.DatabaseException;
import com.luan.exception.UserNotFoundException;
import com.luan.model.User;

public class UserDao {
    private static final String INSERT_USER = """
            INSERT INTO users (name, email)
            VALUES (?, ?)
            RETURNING id
            """;

    private static final String FIND_USER_BY_ID = """
            SELECT id, name, email
            FROM users
            WHERE id = ?
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

    public User findById(long id) {
        try (Connection connection = ConnectionFactory.openConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID)) {

            /* the id replaces the first question mark without changing the SQL text */
            statement.setLong(1, id);

            /* executeQuery is used when the SQL command returns rows */
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new UserNotFoundException(id);
                }

                /* values from the current ResultSet row are mapped into a User object */
                return new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"));
            }
        } catch (SQLException exception) {
            throw new DatabaseException("Could not find the user with id " + id, exception);
        }
    }
}
