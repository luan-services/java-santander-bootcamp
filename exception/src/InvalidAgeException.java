/* extending Exception creates a checked exception */
public class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        /* super passes the explanation to the base Exception class */
        super(message);
    }
}
