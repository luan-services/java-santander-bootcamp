import java.util.Objects;

public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /* value equality allows containsValue to compare users by their fields */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        User user = (User) object;
        return age == user.age && Objects.equals(name, user.name);
    }

    /* equal objects must produce the same hash code */
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    /* toString defines how a user is represented as text */
    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + "}";
    }
}
