import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        showHashMapMethods();
        showPrimitiveAndWrapperTypes();
    }

    private static void showHashMapMethods() {
        /* a HashMap stores key-value pairs and does not guarantee iteration order */
        Map<Integer, User> usersById = new HashMap<>();

        /* put adds a pair or replaces the value when the key already exists */
        usersById.put(1, new User("Alice", 25));
        usersById.put(2, new User("Bob", 30));
        usersById.put(3, new User("Carol", 28));

        /* putIfAbsent adds a pair only when its key is not already present */
        usersById.putIfAbsent(2, new User("Robert", 31));
        usersById.putIfAbsent(4, new User("David", 22));

        /* get returns the value associated with a key or null when it is absent */
        User userWithIdOne = usersById.get(1);

        /* getOrDefault returns a fallback value when the key is absent */
        User unknownUser = usersById.getOrDefault(99, new User("Unknown", 0));

        /* containsKey and containsValue check whether a key or value exists */
        boolean containsIdTwo = usersById.containsKey(2);
        boolean containsAlice = usersById.containsValue(new User("Alice", 25));

        /* replace changes a value only when the key already exists */
        usersById.replace(3, new User("Caroline", 29));

        /* computeIfAbsent creates and stores a value only when its key is absent */
        usersById.computeIfAbsent(5, id -> new User("User " + id, 18));

        /* computeIfPresent updates a value only when its key already exists */
        usersById.computeIfPresent(1,
                (id, user) -> new User(user.getName(), user.getAge() + 1));

        /* remove deletes the pair associated with the given key */
        usersById.remove(4);

        /* keySet, values, and entrySet provide views of keys, values, and pairs */
        System.out.println("Keys: " + usersById.keySet()
                + ", values: " + usersById.values()
                + ", entries: " + usersById.entrySet());

        /* forEach executes an operation for every key-value pair */
        usersById.forEach((id, user) ->
                System.out.println("id = " + id + ", user = " + user));

        System.out.println("get: " + userWithIdOne
                + ", default: " + unknownUser
                + ", contains key 2: " + containsIdTwo
                + ", contains Alice: " + containsAlice
                + ", size: " + usersById.size()
                + ", empty: " + usersById.isEmpty());
    }

    private static void showPrimitiveAndWrapperTypes() {
        /* primitive types store simple values and cannot be null */
        byte primitiveByte = 10;
        short primitiveShort = 100;
        int primitiveInt = 1_000;
        long primitiveLong = 10_000L;
        float primitiveFloat = 10.5F;
        double primitiveDouble = 20.75;
        char primitiveChar = 'A';
        boolean primitiveBoolean = true;

        /* wrapper classes represent primitive values as objects and can be null */
        Byte wrapperByte = primitiveByte;
        Short wrapperShort = primitiveShort;
        Integer wrapperInteger = primitiveInt;
        Long wrapperLong = primitiveLong;
        Float wrapperFloat = primitiveFloat;
        Double wrapperDouble = primitiveDouble;
        Character wrapperCharacter = primitiveChar;
        Boolean wrapperBoolean = primitiveBoolean;

        /* autoboxing automatically converts a primitive into its wrapper object */
        Integer boxedNumber = 42;

        /* unboxing automatically extracts the primitive value from a wrapper */
        int unboxedNumber = boxedNumber;

        /* wrapper utility methods convert text and expose useful constants */
        int parsedNumber = Integer.parseInt("123");
        String numberAsText = Integer.toString(parsedNumber);
        int comparedNumbers = Integer.compare(parsedNumber, unboxedNumber);

        /* generic collections require reference types, so wrappers replace primitives */
        Map<Integer, Boolean> accessByUserId = new HashMap<>();
        accessByUserId.put(1, true);

        /* unboxing a null wrapper throws a NullPointerException */
        Integer nullableNumber = null;

        System.out.println("Primitive values: " + primitiveByte + ", " + primitiveShort
                + ", " + primitiveInt + ", " + primitiveLong + ", " + primitiveFloat
                + ", " + primitiveDouble + ", " + primitiveChar + ", " + primitiveBoolean
                + " | wrappers: " + wrapperByte + ", " + wrapperShort + ", " + wrapperInteger
                + ", " + wrapperLong + ", " + wrapperFloat + ", " + wrapperDouble + ", "
                + wrapperCharacter + ", " + wrapperBoolean
                + " | parsed: " + numberAsText + ", comparison: " + comparedNumbers
                + ", map: " + accessByUserId + ", nullable: " + nullableNumber);
    }
}
