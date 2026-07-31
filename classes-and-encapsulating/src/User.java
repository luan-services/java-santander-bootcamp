public final class User {
    private final String name;
    private final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public boolean equals(Object o) { 
        /* ... implementation ... */
        return true;
    }
    @Override
    public int hashCode() { 
        /* ... implementation ... */ 
        return -1;
    }
    @Override
    public String toString() { 
        /* ... implementation ... */ 
        return "";
    }
}

/* defining a record is the exact same as defining the user class above...

public record User(String name, int age) {

}

key features (automatically generated):

private, final fields for each component
a constructor that initializes all fields
Public accessor methods (Note: they use the field name directly, e.g., user.name() instead of user.getName())
.equals() and hashCode() implementations comparing the state data.
a toString() function that prints out all field names and values nicely

*/


