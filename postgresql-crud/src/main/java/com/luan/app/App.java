package com.luan.app;

import java.sql.SQLException;
import java.util.List;

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
            /* a unique email makes this example safe to execute more than once */
            String temporaryEmail = "temporary." + System.currentTimeMillis() + "@email.com";
            User createdUser = userDao.create(new User("Temporary User", temporaryEmail));
            System.out.println("Created user: " + createdUser);

            User foundUser = userDao.findById(createdUser.getId());
            System.out.println("Found user: " + foundUser);

            List<User> users = userDao.findAll();
            System.out.println("All users: " + users);

            createdUser.setName("Updated User");
            createdUser.setEmail("updated." + System.currentTimeMillis() + "@email.com");
            User updatedUser = userDao.update(createdUser);
            System.out.println("Updated user: " + updatedUser);

            userDao.delete(createdUser.getId());
            System.out.println("Deleted user with id: " + createdUser.getId());

            /* searching after deletion demonstrates the not-found result */
            userDao.findById(createdUser.getId());
        } catch (UserNotFoundException exception) {
            System.out.println("User operation failed: " + exception.getMessage());
        } catch (DatabaseException exception) {
            System.out.println("Database operation failed: " + exception.getMessage());
        }
    }
}
