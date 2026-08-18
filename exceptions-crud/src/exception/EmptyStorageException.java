package exception;

/* this unchecked exception indicates that no users are stored */
public class EmptyStorageException extends RuntimeException {
    public EmptyStorageException() {
        super("The user storage is empty");
    }
}
