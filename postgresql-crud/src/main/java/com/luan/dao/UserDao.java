package com.luan.dao;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.luan.config.ConnectionFactory;
import com.luan.exception.DatabaseException;
import com.luan.exception.DuplicateEmailException;
import com.luan.exception.UserNotFoundException;
import com.luan.model.User;

public class UserDao {
    private static final String UNIQUE_VIOLATION_SQL_STATE = "23505";
    private static final String NO_DATA_FOUND_SQL_STATE = "P0002";

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

    private static final String FIND_ALL_USERS = """
            SELECT id, name, email
            FROM users
            ORDER BY id
            """;

    private static final String UPDATE_USER = """
            UPDATE users
            SET name = ?, email = ?
            WHERE id = ?
            """;

    private static final String DELETE_USER = """
            DELETE FROM users
            WHERE id = ?
            """;

    private static final String CALL_UPDATE_EMAIL_PROCEDURE =
            "CALL update_user_email(?, ?)";

    public User create(User user) {
        try (Connection connection = ConnectionFactory.openConnection()) {
            return insertUser(connection, user);
        } catch (SQLException exception) {
            if (isUniqueViolation(exception)) {
                throw new DuplicateEmailException(user.getEmail(), exception);
            }

            throw new DatabaseException("Could not create the user", exception);
        }
    }

    public List<User> createAll(List<User> users) {
        try (Connection connection = ConnectionFactory.openConnection()) {
            /* disabling auto-commit starts a transaction controlled by this method */
            connection.setAutoCommit(false);

            try {
                for (User user : users) {
                    insertUser(connection, user);
                }

                /* commit makes every successful insert in the transaction permanent */
                connection.commit();
                return users;
            } catch (SQLException exception) {
                /* rollback cancels every insert made since the transaction started */
                rollback(connection, exception);
                users.forEach(user -> user.setId(null));

                if (isUniqueViolation(exception)) {
                    throw new DuplicateEmailException("one of the provided emails", exception);
                }

                throw new DatabaseException("Could not create the users in one transaction", exception);
            }
        } catch (SQLException exception) {
            throw new DatabaseException("Could not start the user transaction", exception);
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

                return mapUser(resultSet);
            }
        } catch (SQLException exception) {
            throw new DatabaseException("Could not find the user with id " + id, exception);
        }
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Connection connection = ConnectionFactory.openConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS);
                ResultSet resultSet = statement.executeQuery()) {

            /* each call to next moves the cursor to another returned row */
            while (resultSet.next()) {
                users.add(mapUser(resultSet));
            }

            /* collection queries return an empty list when no rows exist */
            return users;
        } catch (SQLException exception) {
            throw new DatabaseException("Could not list the users", exception);
        }
    }

    public User update(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("A user id is required for update");
        }

        try (Connection connection = ConnectionFactory.openConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setLong(3, user.getId());

            /* executeUpdate returns the number of rows changed by the command */
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new UserNotFoundException(user.getId());
            }

            return user;
        } catch (SQLException exception) {
            if (isUniqueViolation(exception)) {
                throw new DuplicateEmailException(user.getEmail(), exception);
            }

            throw new DatabaseException("Could not update the user with id " + user.getId(), exception);
        }
    }

    public void delete(long id) {
        try (Connection connection = ConnectionFactory.openConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {

            statement.setLong(1, id);

            /* zero affected rows means that the requested id did not exist */
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new UserNotFoundException(id);
            }
        } catch (SQLException exception) {
            throw new DatabaseException("Could not delete the user with id " + id, exception);
        }
    }

    public void updateEmailWithProcedure(long id, String newEmail) {
        /* CallableStatement is designed to invoke stored procedures */
        try (Connection connection = ConnectionFactory.openConnection();
                CallableStatement statement = connection.prepareCall(CALL_UPDATE_EMAIL_PROCEDURE)) {

            statement.setLong(1, id);
            statement.setString(2, newEmail);
            statement.execute();
        } catch (SQLException exception) {
            if (isUniqueViolation(exception)) {
                throw new DuplicateEmailException(newEmail, exception);
            }

            if (NO_DATA_FOUND_SQL_STATE.equals(exception.getSQLState())) {
                throw new UserNotFoundException(id);
            }

            throw new DatabaseException(
                    "Could not update the user email with the stored procedure", exception);
        }
    }

    /* centralizes the conversion used by queries that return User rows */
    private User mapUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("email"));
    }

    /* inserts using a connection supplied by either a regular or transactional operation */
    private User insertUser(Connection connection, User user) throws SQLException {
        /* question marks keep values separate from the SQL command */
        try (PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
            /* parameter indexes start at one and follow the SQL question marks */
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());

            /* PostgreSQL RETURNING produces a ResultSet containing the generated id */
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new SQLException("The database did not return the generated user id");
                }

                user.setId(resultSet.getLong("id"));
                return user;
            }
        }
    }

    /* preserves a rollback failure as additional information on the original error */
    private void rollback(Connection connection, SQLException originalException) {
        try {
            connection.rollback();
        } catch (SQLException rollbackException) {
            originalException.addSuppressed(rollbackException);
        }
    }

    /* SQLState 23505 is PostgreSQL's standard code for a unique constraint violation */
    private boolean isUniqueViolation(SQLException exception) {
        return UNIQUE_VIOLATION_SQL_STATE.equals(exception.getSQLState());
    }
}
