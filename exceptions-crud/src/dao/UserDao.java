package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.EmptyStorageException;
import exception.UserNotFoundException;
import model.User;

public class UserDao {
    /* the map acts as a simple in-memory database using the user id as its key */
    private final Map<Integer, User> storage = new HashMap<>();

    /* create stores a new user and rejects an id that is already in use */
    public void create(User user) {
        if (storage.containsKey(user.getId())) {
            throw new IllegalArgumentException("User id already exists: " + user.getId());
        }

        storage.put(user.getId(), user);
    }

    /* findById returns one user or throws an exception when the id is absent */
    public User findById(int id) {
        User user = storage.get(id);

        if (user == null) {
            throw new UserNotFoundException(id);
        }

        return user;
    }

    /* findAll returns a copy so callers cannot change the internal map directly */
    public List<User> findAll() {
        if (storage.isEmpty()) {
            throw new EmptyStorageException();
        }

        return new ArrayList<>(storage.values());
    }

    /* update replaces the stored data only when the user already exists */
    public void update(User user) {
        if (!storage.containsKey(user.getId())) {
            throw new UserNotFoundException(user.getId());
        }

        storage.put(user.getId(), user);
    }

    /* delete removes and returns a user or throws when the id is absent */
    public User delete(int id) {
        User removedUser = storage.remove(id);

        if (removedUser == null) {
            throw new UserNotFoundException(id);
        }

        return removedUser;
    }
}
