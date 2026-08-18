package com.luan.app;

import java.sql.SQLException;

import org.flywaydb.core.api.output.MigrateResult;

import com.luan.config.DatabaseHealthCheck;
import com.luan.config.DatabaseMigration;
import com.luan.dao.UserDao;
import com.luan.exception.DatabaseException;
import com.luan.exception.UserNotFoundException;
import com.luan.model.User;

public class App {
    public static void main(String[] args) {
        /* migrations run before code that depends on the database structure */
        MigrateResult result = DatabaseMigration.migrate();

        System.out.println("Database migration completed. Migrations executed: "
                + result.migrationsExecuted);

        try {
            boolean databaseAvailable = DatabaseHealthCheck.isDatabaseAvailable();
            System.out.println("JDBC connection available: " + databaseAvailable);
        } catch (SQLException exception) {
            System.out.println("JDBC connection failed: " + exception.getMessage());
        }

        UserDao userDao = new UserDao();

        try {
            User foundUser = userDao.findById(1);
            System.out.println("Found user: " + foundUser);

            /* this query demonstrates the exception used when no row is returned */
            userDao.findById(99);
        } catch (UserNotFoundException exception) {
            System.out.println("User search failed: " + exception.getMessage());
        } catch (DatabaseException exception) {
            System.out.println("Database operation failed: " + exception.getMessage());
        }
    }
}
