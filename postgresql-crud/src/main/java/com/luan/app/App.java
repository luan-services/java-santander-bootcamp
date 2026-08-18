package com.luan.app;

import java.sql.SQLException;
import java.util.List;

import org.flywaydb.core.api.output.MigrateResult;

import com.luan.config.DatabaseHealthCheck;
import com.luan.config.DatabaseMigration;
import com.luan.dao.UserDao;
import com.luan.dao.UserSummaryDao;
import com.luan.exception.DatabaseException;
import com.luan.exception.DuplicateEmailException;
import com.luan.exception.UserNotFoundException;
import com.luan.model.User;
import com.luan.model.UserSummary;

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

        /* Alice already owns this email, so PostgreSQL rejects the duplicate */
        try {
            userDao.create(new User("Another Alice", "alice@email.com"));
        } catch (DuplicateEmailException exception) {
            System.out.println("Duplicate email: " + exception.getMessage());
        }

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
        } catch (UserNotFoundException exception) {
            System.out.println("User operation failed: " + exception.getMessage());
        } catch (DuplicateEmailException exception) {
            System.out.println("Duplicate email: " + exception.getMessage());
        } catch (DatabaseException exception) {
            System.out.println("Database operation failed: " + exception.getMessage());
        }

        /* both users are committed because every insert succeeds */
        List<User> committedUsers = List.of(
                new User("Committed User One", "committed.one@email.com"),
                new User("Committed User Two", "committed.two@email.com"));

        try {
            userDao.createAll(committedUsers);
            System.out.println("Committed transaction: " + committedUsers);
            committedUsers.forEach(user -> userDao.delete(user.getId()));
        } catch (DuplicateEmailException | DatabaseException exception) {
            System.out.println("Commit example failed: " + exception.getMessage());
        }

        /* the duplicate email makes the second insert fail and rolls back the first */
        String rollbackEmail = "rollback." + System.currentTimeMillis() + "@email.com";
        List<User> rolledBackUsers = List.of(
                new User("Rolled Back User", rollbackEmail),
                new User("Duplicate Alice", "alice@email.com"));

        try {
            userDao.createAll(rolledBackUsers);
        } catch (DuplicateEmailException exception) {
            boolean firstInsertWasRolledBack = userDao.findAll().stream()
                    .noneMatch(user -> user.getEmail().equals(rollbackEmail));
            System.out.println("Rolled back transaction: " + firstInsertWasRolledBack);
        }

        /* a view can be queried without reproducing its formatting expression in Java */
        UserSummaryDao userSummaryDao = new UserSummaryDao();
        List<UserSummary> summaries = userSummaryDao.findAll();
        System.out.println("User summary view: " + summaries);

        /* CallableStatement delegates this email update to a PostgreSQL procedure */
        String procedureEmail = "procedure." + System.currentTimeMillis() + "@email.com";
        User procedureUser = userDao.create(new User("Procedure User", procedureEmail));
        String updatedProcedureEmail = "updated." + procedureEmail;

        try {
            userDao.updateEmailWithProcedure(procedureUser.getId(), updatedProcedureEmail);
            User updatedProcedureUser = userDao.findById(procedureUser.getId());
            System.out.println("Procedure result: " + updatedProcedureUser);
        } finally {
            /* cleanup runs even if the procedure example fails */
            userDao.delete(procedureUser.getId());
        }
    }
}
