public class App {
    public static void main(String[] args) {
        showCheckedException();
        showUncheckedExceptions();
        showCustomRuntimeException();
        showFinallyBlock();
    }

    private static void showCheckedException() {
        /* checked exceptions are verified by the compiler */
        /* the caller must catch them or declare them with throws */
        try {
            registerUser("Alice", 16);
        } catch (InvalidAgeException exception) {
            System.out.println("Checked exception: " + exception.getMessage());
        }
    }

    private static void registerUser(String name, int age) throws InvalidAgeException {
        /* throw creates and signals an exception when a rule is violated */
        if (age < 18) {
            throw new InvalidAgeException(name + " must be at least 18 years old");
        }

        System.out.println("Registered user: " + name);
    }

    private static void showUncheckedExceptions() {
        /* runtime exceptions are unchecked and are not enforced by the compiler */
        /* they commonly indicate invalid input or a programming error */
        try {
            int number = Integer.parseInt("not a number");
            System.out.println(number);
        } catch (NumberFormatException exception) {
            System.out.println("Unchecked exception: invalid numeric text");
        }

        try {
            int result = 10 / 0;
            System.out.println(result);
        } catch (ArithmeticException exception) {
            System.out.println("Unchecked exception: division by zero");
        }
    }

    private static void showCustomRuntimeException() {
        try {
            double remainingBalance = withdraw(100.0, 150.0);
            System.out.println("Remaining balance: " + remainingBalance);
        } catch (InsufficientBalanceException exception) {
            System.out.println("Custom runtime exception: " + exception.getMessage());
        }
    }

    private static double withdraw(double balance, double amount) {
        /* IllegalArgumentException is appropriate for an invalid method argument */
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        /* a custom runtime exception describes a domain-specific invalid operation */
        if (amount > balance) {
            throw new InsufficientBalanceException("The account does not have enough balance");
        }

        return balance - amount;
    }

    private static void showFinallyBlock() {
        try {
            System.out.println("Running an operation");
        } finally {
            /* finally normally runs whether an exception occurs or not */
            /* it is used for cleanup, but try-with-resources is preferred for resources */
            System.out.println("Finally block: cleanup completed");
        }
    }
}
