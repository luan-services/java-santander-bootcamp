import java.util.Objects;

public class People {
    private String name;
    private int age;

    public People(String name, int age) {
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

    /*
     * overrides Object.equals so two different People instances can be
     * considered equal when their name and age have the same values
     */
    @Override
    public boolean equals(Object object) {
        /* returns true immediately when both references point to the same instance */
        if (this == object) {
            return true;
        }

        /* prevents comparisons with null or objects from another class */
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        /* casts the object after confirming that it is a People instance */
        People people = (People) object;

        /* uses the fields that define equality for this class */
        return age == people.age && Objects.equals(name, people.name);
    }

    /*
     * equal objects must have equal hash codes, so hashCode uses the same
     * fields used by equals; equal hashes alone do not guarantee equal objects
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
