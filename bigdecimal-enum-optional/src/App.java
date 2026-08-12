import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        showBigDecimal();
        showEnums();
        showOptional();
    }

    private static void showBigDecimal() {
        /* double stores binary floating-point approximations, so many decimal values are not exact */
        double impreciseResult = 0.1 + 0.2;

        /* BigDecimal created from text stores the intended decimal value exactly */
        BigDecimal firstValue = new BigDecimal("0.1");
        BigDecimal secondValue = new BigDecimal("0.2");
        BigDecimal preciseResult = firstValue.add(secondValue);

        /* BigDecimal is immutable, so every operation returns a new object */
        BigDecimal price = new BigDecimal("19.90");
        BigDecimal quantity = new BigDecimal("3");
        BigDecimal subtotal = price.multiply(quantity);
        BigDecimal discount = subtotal.multiply(new BigDecimal("0.10"));
        BigDecimal total = subtotal.subtract(discount);

        /* division needs a scale and rounding rule when the decimal result does not terminate */
        BigDecimal dividedValue = BigDecimal.ONE.divide(
                new BigDecimal("3"), 2, RoundingMode.HALF_UP);

        /* compareTo compares numeric values while equals also compares their scales */
        BigDecimal valueWithOneDecimal = new BigDecimal("2.0");
        BigDecimal valueWithTwoDecimals = new BigDecimal("2.00");
        boolean sameNumericValue = valueWithOneDecimal.compareTo(valueWithTwoDecimals) == 0;
        boolean sameValueAndScale = valueWithOneDecimal.equals(valueWithTwoDecimals);

        /* valueOf is safer than new BigDecimal(double) when starting from a double */
        BigDecimal convertedDouble = BigDecimal.valueOf(0.1);

        System.out.println("BigDecimal: double 0.1 + 0.2 = " + impreciseResult
                + ", decimal result = " + preciseResult
                + ", subtotal = " + subtotal
                + ", discount = " + discount
                + ", total = " + total
                + ", 1 / 3 = " + dividedValue
                + ", compareTo = " + sameNumericValue
                + ", equals = " + sameValueAndScale
                + ", converted = " + convertedDouble);
    }

    private static void showEnums() {
        /* a basic enum needs only a fixed list of constants */
        Direction direction = Direction.NORTH;

        /* basic enum constants have no custom value, field, or declared constructor */
        boolean isNorth = direction == Direction.NORTH;

        /* an enum defines a fixed and type-safe set of instances */
        OrderStatus status = OrderStatus.PAID;

        /* enum constants can be converted to text and recovered by their exact names */
        String statusName = status.name();
        OrderStatus parsedStatus = OrderStatus.valueOf("SHIPPED");

        /* values returns every constant in declaration order */
        int numberOfStatuses = OrderStatus.values().length;

        /* enums may have fields, constructors, methods, and implemented interfaces */
        String statusDescription = status.getDescription();

        /* an interface defines behavior that many unrelated classes can implement */
        /* an enum defines the limited instances that exist and may also implement behavior */
        BigDecimal left = new BigDecimal("10");
        BigDecimal right = new BigDecimal("4");
        BigDecimal addition = Operation.ADD.apply(left, right);
        BigDecimal subtraction = Operation.SUBTRACT.apply(left, right);
        BigDecimal multiplication = Operation.MULTIPLY.apply(left, right);
        BigDecimal division = Operation.DIVIDE.apply(left, right);

        System.out.println("Enums: direction = " + direction
                + ", is north = " + isNorth
                + ", status = " + statusName
                + ", parsed = " + parsedStatus
                + ", count = " + numberOfStatuses
                + ", description = " + statusDescription
                + ", add = " + addition
                + ", subtract = " + subtraction
                + ", multiply = " + multiplication
                + ", divide = " + division);
    }

    private static void showOptional() {
        /* Optional represents a value that may be present or absent without returning null */
        Optional<String> presentName = Optional.of("Alice");
        Optional<String> absentName = Optional.empty();
        Optional<String> nullableName = Optional.ofNullable(null);

        /* map transforms a present value and keeps an absent Optional empty */
        String upperCaseName = presentName.map(String::toUpperCase).orElse("UNKNOWN");

        /* filter keeps a present value only when it matches the condition */
        boolean hasLongName = presentName.filter(name -> name.length() > 3).isPresent();

        /* orElse returns a fallback value but evaluates it even when a value is present */
        String defaultName = absentName.orElse("Guest");

        /* orElseGet creates the fallback lazily only when the Optional is empty */
        String lazyDefaultName = nullableName.orElseGet(() -> "Generated guest");

        /* ifPresent executes an action only when a value exists */
        presentName.ifPresent(name -> System.out.println("Present name: " + name));

        /* orElseThrow is useful when an absent value must be treated as an error */
        String requiredName = presentName.orElseThrow(
                () -> new IllegalStateException("Name is required"));

        /* Optional is intended mainly for return types, not fields or method parameters */
        System.out.println("Optional: upper = " + upperCaseName
                + ", long name = " + hasLongName
                + ", default = " + defaultName
                + ", lazy default = " + lazyDefaultName
                + ", required = " + requiredName
                + ", empty = " + absentName.isEmpty());
    }
}
