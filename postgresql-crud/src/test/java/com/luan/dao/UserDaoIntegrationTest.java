package com.luan.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.luan.config.DatabaseMigration;
import com.luan.exception.DuplicateEmailException;
import com.luan.exception.UserNotFoundException;
import com.luan.model.User;

class UserDaoIntegrationTest {
    private final UserDao userDao = new UserDao();
    private final List<Long> userIdsToDelete = new ArrayList<>();

    @BeforeAll
    static void migrateDatabase() {
        /* tests require the same database structure used by the application */
        DatabaseMigration.migrate();
    }

    @AfterEach
    void deleteCreatedUsers() {
        /* cleanup keeps one test independent from the next test */
        for (Long id : userIdsToDelete) {
            try {
                userDao.delete(id);
            } catch (UserNotFoundException exception) {
                /* the delete test already removed this user */
            }
        }
    }

    @Test
    void shouldCreateAndFindUser() {
        User createdUser = createTestUser("Create User");
        User foundUser = userDao.findById(createdUser.getId());

        assertNotNull(createdUser.getId());
        assertEquals(createdUser.getId(), foundUser.getId());
        assertEquals(createdUser.getEmail(), foundUser.getEmail());
    }

    @Test
    void shouldListCreatedUser() {
        User createdUser = createTestUser("List User");

        boolean userWasListed = userDao.findAll().stream()
                .anyMatch(user -> user.getId().equals(createdUser.getId()));

        assertFalse(userDao.findAll().isEmpty());
        assertEquals(true, userWasListed);
    }

    @Test
    void shouldUpdateUser() {
        User user = createTestUser("Original Name");
        user.setName("Updated Name");

        userDao.update(user);
        User updatedUser = userDao.findById(user.getId());

        assertEquals("Updated Name", updatedUser.getName());
    }

    @Test
    void shouldDeleteUser() {
        User user = createTestUser("Delete User");

        userDao.delete(user.getId());

        assertThrows(UserNotFoundException.class, () -> userDao.findById(user.getId()));
    }

    @Test
    void shouldRejectDuplicateEmail() {
        String email = uniqueEmail();
        User firstUser = userDao.create(new User("First User", email));
        userIdsToDelete.add(firstUser.getId());

        assertThrows(
                DuplicateEmailException.class,
                () -> userDao.create(new User("Second User", email)));
    }

    private User createTestUser(String name) {
        User user = userDao.create(new User(name, uniqueEmail()));
        userIdsToDelete.add(user.getId());
        return user;
    }

    private String uniqueEmail() {
        return "test." + UUID.randomUUID() + "@email.com";
    }
}
