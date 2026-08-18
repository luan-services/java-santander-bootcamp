package exception;

/* this unchecked exception indicates that the requested user does not exist */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("User with id " + id + " was not found");
    }
}
