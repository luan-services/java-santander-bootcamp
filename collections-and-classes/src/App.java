import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        showArrayMethods();
        showListMethods();
        showEqualsBehavior();
        showHashBehavior();
    }

    private static void showArrayMethods() {
        /* creates an array with a fixed size and initial values */
        String[] languages = { "Java", "Python", "C#", "JavaScript" };

        /* sorts the original array in ascending order */
        Arrays.sort(languages);

        /* searches a sorted array and returns the element index */
        int javaIndex = Arrays.binarySearch(languages, "Java");

        /* creates a copy with the specified length */
        String[] languagesCopy = Arrays.copyOf(languages, languages.length);

        /* copies the elements from the start index to the exclusive end index */
        String[] firstTwoLanguages = Arrays.copyOfRange(languages, 0, 2);

        /* compares the values and order of the elements in both arrays */
        boolean arraysAreEqual = Arrays.equals(languages, languagesCopy);

        /* creates an array with four positions initialized with zero */
        int[] grades = new int[4];

        /* fills every position with the same value */
        Arrays.fill(grades, 7);

        /* accesses an array position by index and replaces its value */
        grades[0] = 10;

        System.out.println("Array methods: " + Arrays.toString(languages)
                + ", Java index = " + javaIndex
                + ", first two = " + Arrays.toString(firstTwoLanguages)
                + ", copy equals original = " + arraysAreEqual
                + ", grades = " + Arrays.toString(grades));
    }

    private static void showListMethods() {
        /* creates an empty list that can grow or shrink */
        List<String> languages = new ArrayList<>();

        /* adds one element to the end of the list */
        languages.add("Java");

        /* adds all elements from another collection */
        languages.addAll(Arrays.asList("Python", "C#", "JavaScript"));

        /* inserts an element at a specific index */
        languages.add(1, "Kotlin");

        /* replaces the element at a specific index */
        languages.set(2, "Go");

        /* removes the first element that matches the given value */
        languages.remove("C#");

        /* removes every element that matches the condition */
        languages.removeIf(language -> language.startsWith("JavaS"));

        /* applies the given operation to every element */
        languages.replaceAll(String::toUpperCase);

        /* sorts the original list in ascending order */
        Collections.sort(languages);

        /* checks whether the list contains an equal element */
        boolean containsJava = languages.contains("JAVA");

        /* returns the index of an element or -1 when it is not found */
        int kotlinIndex = languages.indexOf("KOTLIN");

        /* creates a new list from a portion of the original list */
        List<String> firstTwoLanguages = new ArrayList<>(languages.subList(0, 2));

        System.out.println("List methods: " + languages
                + ", contains JAVA = " + containsJava
                + ", Kotlin index = " + kotlinIndex
                + ", first two = " + firstTwoLanguages);
    }

    private static void showEqualsBehavior() {
        /* string overrides equals, so its content is compared */
        List<String> firstNames = new ArrayList<>(Arrays.asList("Alice", "Bob"));
        List<String> secondNames = new ArrayList<>(Arrays.asList("Alice", "Bob"));

        /* list compares its elements in the same order using their equals method */
        boolean stringListsAreEqual = firstNames.equals(secondNames);

        /* user inherits Object.equals, so different instances are not equal */
        List<User> firstUsers = new ArrayList<>();
        firstUsers.add(new User("Alice", 25));

        List<User> secondUsers = new ArrayList<>();
        secondUsers.add(new User("Alice", 25));

        /* list equality is false because the contained users are different instances */
        boolean userListsAreEqual = firstUsers.equals(secondUsers);

        /* the same User instance is equal to itself */
        User sharedUser = new User("Bob", 30);
        List<User> thirdUsers = new ArrayList<>(Arrays.asList(sharedUser));
        List<User> fourthUsers = new ArrayList<>(Arrays.asList(sharedUser));
        boolean listsWithSameInstanceAreEqual = thirdUsers.equals(fourthUsers);

        System.out.println("String lists with equal values: " + stringListsAreEqual
                + ", User lists with equal values: " + userListsAreEqual
                + ", User lists with the same instance: " + listsWithSameInstanceAreEqual);
    }

    private static void showHashBehavior() {
        People firstPerson = new People("Alice", 25);
        People secondPerson = new People("Alice", 25);

        /* equals compares the field values instead of the object identities */
        boolean peopleAreEqual = firstPerson.equals(secondPerson);

        /* equal objects must return the same hash code */
        boolean hashesAreEqual = firstPerson.hashCode() == secondPerson.hashCode();

        /*
         * hashSet uses hashCode to find the storage area and equals to confirm
         * whether an equal element already exists in that area
         */
        Set<People> people = new HashSet<>();
        people.add(firstPerson);
        people.add(secondPerson);

        /* the set keeps only one element because both objects are equal */
        System.out.println("People are equal: " + peopleAreEqual
                + ", hashes are equal: " + hashesAreEqual
                + ", HashSet size: " + people.size());
    }
}
