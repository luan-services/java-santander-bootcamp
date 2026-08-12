import java.time.Duration;
import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {
        showStringMethods();
        showStringBuilderMethods();
        showStringBufferMethods();
        compareConcatenationPerformance();
    }

    private static void showStringMethods() {
        /* String stores an immutable sequence of characters */
        String originalText = "  Java Programming  ";

        /* methods return new strings because the original String cannot be changed */
        String trimmedText = originalText.trim();
        String upperCaseText = trimmedText.toUpperCase();
        String replacedText = trimmedText.replace("Java", "Object-Oriented");
        String substring = trimmedText.substring(0, 4);

        /* length returns the number of characters in the string */
        int characterCount = trimmedText.length();

        /* charAt returns the character at a specific index */
        char firstCharacter = trimmedText.charAt(0);

        /* indexOf returns the first matching index or -1 when no match exists */
        int programmingIndex = trimmedText.indexOf("Programming");

        /* contains, startsWith, and endsWith test parts of the text */
        boolean containsJava = trimmedText.contains("Java");
        boolean startsWithJava = trimmedText.startsWith("Java");
        boolean endsWithProgramming = trimmedText.endsWith("Programming");

        /* split separates a string into an array using a delimiter */
        String[] words = trimmedText.split(" ");

        /* join combines multiple values using a delimiter */
        String joinedWords = String.join("-", words);

        /* valueOf converts another value into its String representation */
        String numberAsText = String.valueOf(42);

        /* string literals with equal content usually share one object in the string pool */
        String firstLiteral = "Java";
        String secondLiteral = "Java";

        /* new String creates a different object even when its content is equal */
        String separateObject = new String("Java");

        /* == compares references while equals compares character content */
        boolean literalsShareReference = firstLiteral == secondLiteral;
        boolean objectsShareReference = firstLiteral == separateObject;
        boolean objectsHaveEqualContent = firstLiteral.equals(separateObject);

        /* equalsIgnoreCase compares content without considering letter case */
        boolean equalIgnoringCase = firstLiteral.equalsIgnoreCase("JAVA");

        /* isEmpty checks length zero while isBlank also accepts only whitespace */
        boolean emptyText = "".isEmpty();
        boolean blankText = "   ".isBlank();

        System.out.println("String: original = '" + originalText
                + "', trimmed = '" + trimmedText
                + "', upper = '" + upperCaseText
                + "', replaced = '" + replacedText
                + "', substring = '" + substring
                + "', length = " + characterCount
                + ", first character = " + firstCharacter
                + ", index = " + programmingIndex
                + ", contains = " + containsJava
                + ", starts = " + startsWithJava
                + ", ends = " + endsWithProgramming
                + ", joined = " + joinedWords
                + ", number = " + numberAsText
                + ", literal references = " + literalsShareReference
                + ", new reference = " + objectsShareReference
                + ", equal content = " + objectsHaveEqualContent
                + ", ignore case = " + equalIgnoringCase
                + ", empty = " + emptyText
                + ", blank = " + blankText);
    }

    private static void showStringBuilderMethods() {
        /* StringBuilder is mutable and efficient for repeated text modifications */
        StringBuilder builder = new StringBuilder("Java");

        /* append adds content to the end without creating a new builder */
        builder.append(" Programming");

        /* insert adds content at a specific index */
        builder.insert(4, " Language");

        /* replace changes the characters between two indexes */
        builder.replace(0, 4, "Kotlin");

        /* delete removes the characters between two indexes */
        builder.delete(6, 15);

        /* setCharAt changes one character in the same mutable object */
        builder.setCharAt(0, 'k');

        /* length is the current text size and capacity is the allocated storage */
        int length = builder.length();
        int capacity = builder.capacity();

        /* reverse reverses the character sequence in the same object */
        String reversedText = new StringBuilder(builder).reverse().toString();

        /* toString creates an immutable String from the builder content */
        String finalText = builder.toString();

        System.out.println("StringBuilder: text = '" + finalText
                + "', reversed = '" + reversedText
                + "', length = " + length
                + ", capacity = " + capacity);
    }

    private static void showStringBufferMethods() {
        /* StringBuffer is mutable like StringBuilder but its methods are synchronized */
        StringBuffer buffer = new StringBuffer("Shared");

        /* synchronized methods make individual operations safe for concurrent access */
        buffer.append(" text");
        buffer.insert(0, "Mutable ");
        buffer.replace(8, 14, "shared");

        /* StringBuffer offers similar methods to StringBuilder */
        int length = buffer.length();
        int capacity = buffer.capacity();
        String finalText = buffer.toString();

        /* StringBuilder is usually preferred when only one thread modifies the text */
        /* StringBuffer is useful when multiple threads share and modify one instance */
        System.out.println("StringBuffer: text = '" + finalText
                + "', length = " + length
                + ", capacity = " + capacity);
    }

    private static void compareConcatenationPerformance() {
        int[] numbers = new int[10_000];

        /* fills the array with the same values used by both tests */
        for (int index = 0; index < numbers.length; index++) {
            numbers[index] = index;
        }

        /* records the date and time immediately before regular concatenation */
        LocalDateTime stringStart = LocalDateTime.now();
        String concatenatedString = "";

        /* each concatenation creates a new String and copies the previous content */
        for (int number : numbers) {
            concatenatedString += number;
        }

        /* calculates the elapsed time between the two date-time values */
        LocalDateTime stringEnd = LocalDateTime.now();
        long stringNanoseconds = Duration.between(stringStart, stringEnd).toNanos();

        /* records the date and time immediately before builder concatenation */
        LocalDateTime builderStart = LocalDateTime.now();
        StringBuilder builder = new StringBuilder();

        /* append modifies one buffer instead of creating a new String each time */
        for (int number : numbers) {
            builder.append(number);
        }

        /* converts the completed mutable sequence into one immutable String */
        String builderString = builder.toString();
        LocalDateTime builderEnd = LocalDateTime.now();
        long builderNanoseconds = Duration.between(builderStart, builderEnd).toNanos();

        /* this simple timing example may vary between runs and is not a formal benchmark */
        System.out.println("Concatenation comparison: String = " + stringNanoseconds
                + " ns, StringBuilder = " + builderNanoseconds
                + " ns, same result = " + concatenatedString.equals(builderString)
                + ", result length = " + builderString.length());
    }
}
