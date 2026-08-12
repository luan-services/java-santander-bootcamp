import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        showStreamMethods();
        showGenerics();
    }

    private static void showStreamMethods() {
        List<String> languages = Arrays.asList(
                "Java", "Python", "Java", "JavaScript", "Go", "Kotlin");

        /* a stream processes data as a pipeline without changing the original list */
        List<String> processedLanguages = languages.stream()
                /* filter keeps only elements that match a condition */
                .filter(language -> language.length() > 3)
                /* map transforms each element into another value */
                .map(String::toUpperCase)
                /* distinct removes repeated elements according to equals */
                .distinct()
                /* sorted orders the elements */
                .sorted()
                /* limit keeps only the requested number of elements */
                .limit(3)
                /* collect is a terminal operation that stores the stream result */
                .collect(Collectors.toList());

        /* intermediate operations are lazy and run only after a terminal operation */
        long shortNameCount = languages.stream()
                .filter(language -> language.length() <= 4)
                /* count is a terminal operation that returns the number of elements */
                .count();

        /* reduce combines all elements into one result */
        int total = Arrays.asList(10, 20, 30, 40).stream()
                .reduce(0, Integer::sum);

        /* findFirst returns an Optional because the stream may be empty */
        Optional<String> firstJavaLanguage = languages.stream()
                .filter(language -> language.startsWith("Java"))
                .findFirst();

        /* anyMatch checks whether at least one element matches a condition */
        boolean containsPython = languages.stream()
                .anyMatch(language -> language.equals("Python"));

        /* forEach is useful for a final action but not for building a new collection */
        processedLanguages.forEach(language -> System.out.println("Language: " + language));

        /* streams are best for filtering, transforming, searching, and aggregating data */
        /* a simple loop can be clearer when the operation changes external state */
        System.out.println("Stream results: original = " + languages
                + ", processed = " + processedLanguages
                + ", short names = " + shortNameCount
                + ", total = " + total
                + ", first Java language = " + firstJavaLanguage.orElse("Not found")
                + ", contains Python = " + containsPython);
    }

    private static void showGenerics() {
        /* generics allow one class to work safely with different reference types */
        Box<String> messageBox = new Box<>("Hello");
        Box<Integer> numberBox = new Box<>(42);

        /* the type argument determines which value the box accepts and returns */
        String message = messageBox.getValue();
        Integer number = numberBox.getValue();

        /* generics provide compile-time type safety and avoid manual casts */
        messageBox.setValue("Updated message");
        numberBox.setValue(100);

        System.out.println("Generic boxes: message = " + message
                + ", number = " + number
                + ", updated message = " + messageBox.getValue()
                + ", updated number = " + numberBox.getValue());
    }
}
