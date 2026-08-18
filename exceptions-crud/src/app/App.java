package app;

import dao.UserDao;
import exception.EmptyStorageException;
import exception.UserNotFoundException;
import model.User;

public class App {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        /* create */
        userDao.create(new User(1, "Alice", "alice@email.com"));
        userDao.create(new User(2, "Bob", "bob@email.com"));
        System.out.println("Created users: " + userDao.findAll());

        /* read */
        User foundUser = userDao.findById(1);
        System.out.println("Found user: " + foundUser);

        /* update */
        userDao.update(new User(1, "Alice Smith", "alice.smith@email.com"));
        System.out.println("Updated user: " + userDao.findById(1));

        /* delete */
        User deletedUser = userDao.delete(2);
        System.out.println("Deleted user: " + deletedUser);

        /* a missing id is handled without stopping the application */
        try {
            userDao.findById(99);
        } catch (UserNotFoundException exception) {
            System.out.println("Error: " + exception.getMessage());
        }

        userDao.delete(1);

        /* an empty storage has its own domain-specific exception */
        try {
            userDao.findAll();
        } catch (EmptyStorageException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}
